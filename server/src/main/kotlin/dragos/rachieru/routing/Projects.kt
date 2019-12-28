package dragos.rachieru.routing

import data.Project
import dragos.rachieru.database.ProjectsTable
import dragos.rachieru.database.RolesTable
import dragos.rachieru.database.UsersTable
import dragos.rachieru.model.BaseResponse
import dragos.rachieru.model.CompletableResponse
import dragos.rachieru.model.User
import io.ktor.application.call
import io.ktor.http.Parameters
import io.ktor.http.auth.parseAuthorizationHeader
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */

fun Route.routeProjects() {
    get("projects") {
        try {
            val arrayProjects = getAllProject()
            call.respond(
                arrayProjects
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                CompletableResponse.error(
                    listOf(e.message ?: "unknown error")
                )
            )
        }
    }
    post("projects") {
        call.respond(BaseResponse.success(insertProject(call.receiveParameters())))
    }
}

fun getAllProject() = transaction{
    ProjectsTable.selectAll().map {
        Project(
            _id = it[ProjectsTable.id],
            name = it[ProjectsTable.name],
            description = it[ProjectsTable.description],
            creator_id = it[ProjectsTable.creatorId]
        )
    }
}

fun insertProject(parameters: Parameters) = transaction {
    ProjectsTable.insert {
        it[name] = parameters["name"]!!
        it[description] = parameters["description"]!!
        it[creatorId] = parameters["creatorId"]!!.toLong()
    }
}