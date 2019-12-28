package dragos.rachieru.routing

import dragos.rachieru.database.ProjectsTable
import dragos.rachieru.mapper.toProject
import dragos.rachieru.model.*
import io.ktor.application.call
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
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

@UnstableDefault
fun Route.routeProjects() {
    get("projects") {
        try {
            val user = call.principal<User>()!!
            val queries = call.request.queryParameters
            val limit = queries["limit"]?.toInt() ?: 10
            val lastId = queries["last_id"]?.toLong() ?: 0L
            val arrayProjects = getMyProjects(
                limit,
                lastId,
                user.id
            )
            call.respond(
                Json.stringify(
                    ListResponse.serializer(Project.serializer()),
                    ListResponse.success(arrayProjects, pagination = PaginationResponse(limit, lastId))
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                HttpStatusCode.BadRequest,
                CompletableResponse.error(
                    listOf(e.message ?: "unknown error")
                )
            )
        }
    }
    post("projects") {
        val user = call.principal<User>()!!
        call.respond(
            Json.stringify(
                BaseResponse.serializer(Project.serializer()),
                BaseResponse.success(insertProject(user.id, call.receiveParameters()))
            )
        )
    }
}

fun getMyProjects(
    limit: Int,
    lastId: Long,
    creatorId: Long
) = transaction {
    ProjectsTable.select {
        ProjectsTable.creatorId eq creatorId
        ProjectsTable.id greater lastId
    }
        .limit(limit)
        .map {
            it.toProject()
        }
}

fun insertProject(userId: Long, parameters: Parameters) = transaction {
    val project = Project(
        id = System.currentTimeMillis(),
        name = parameters["name"]!!,
        description = parameters["description"]!!,
        creatorId = userId
    )
    ProjectsTable.insert {
        it[id] = System.currentTimeMillis()
        it[name] = project.name
        it[description] = project.description
        it[creatorId] = userId
    }
    project
}