package dragos.rachieru.routing

import dragos.rachieru.auth.user
import dragos.rachieru.database.IssuesTable
import dragos.rachieru.entity.IssueEntity
import dragos.rachieru.model.BaseResponse
import dragos.rachieru.model.ListResponse
import dragos.rachieru.model.PaginationResponse
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
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

fun Route.routeIssues() {
    route("{project_id}/issues") {
        get {
            val user = call.user!!
            val limit = call.request.queryParameters["limit"]?.toInt() ?: 10
            val issues = transaction {
                IssueEntity.find {
                    IssuesTable.project eq call.parameters["project_id"]!!.toLong()
                }.toList()
            }
            call.respond(
                ListResponse.success(issues, PaginationResponse(limit, 0))
            )
        }
        post {
            val user = call.user!!
            val issue = transaction {
                IssueEntity.new {
                    updatedAt = null
                    createdAt = System.currentTimeMillis()
                    creator = user
                }
            }
            call.respond(
                BaseResponse.success(
                    issue
                )
            )
        }
    }
}