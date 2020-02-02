package io.scrunchy.server.routing

import com.squareup.moshi.Moshi
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.scrunchy.common.PaginationResponse
import io.scrunchy.server.auth.user
import io.scrunchy.server.database.ProjectsTable
import io.scrunchy.server.entity.ProjectEntity
import io.scrunchy.server.entity.UserEntity
import io.scrunchy.server.moshi.dataResponse
import io.scrunchy.server.moshi.errorResponse
import io.scrunchy.server.moshi.listDataResponse
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Scrunchy
 *
 * Copyright (C) 2019  Rachieru Dragos-Mihai
 *
 * Scrunchy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Scrunchy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Scrunchy.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */

fun Route.routeProjects(moshi: Moshi) {
    route("projects") {
        get {
            try {
                val user = call.user!!
                val queries = call.request.queryParameters
                val limit = queries["limit"]?.toInt() ?: 10
                val lastId = queries["last_id"]?.toLong() ?: 0L
                val arrayProjects = getMyProjects(
                    limit,
                    lastId,
                    user.userId
                ).toList().map {
                    it.toProject()
                }
                call.respond(
                    moshi.listDataResponse(
                        arrayProjects, pagination = PaginationResponse(
                            limit,
                            lastId
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(
                    HttpStatusCode.BadRequest,
                    moshi.errorResponse(
                        listOf(e.message ?: "unknown error")
                    )
                )
            }
        }
        post {
            val user = call.user!!
            call.respond(
                moshi.dataResponse(
                    insertProject(
                        user,
                        call.receiveParameters()
                    ).toProject()
                )
            )
        }
        routeIssues(moshi)
    }
}

fun getMyProjects(
    limit: Int,
    lastId: Long,
    creatorId: Long
) = transaction {
    ProjectEntity.find {
        ProjectsTable.creator eq creatorId
        ProjectsTable.id greater lastId
    }
        .limit(limit)
        .toList()
}

fun insertProject(user: UserEntity, parameters: Parameters) = transaction {
    ProjectEntity.new {
        name = parameters["name"]!!
        description = parameters["description"]!!
        creator = user
    }
}