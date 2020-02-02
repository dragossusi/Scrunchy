package io.scrunchy.server.routing

import com.squareup.moshi.Moshi
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.scrunchy.common.PaginationResponse
import io.scrunchy.server.entity.UserEntity
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

fun Route.routeUsers(moshi: Moshi) {
    get("/users") {
        try {
            call.respond(
                moshi.listDataResponse(
                    getUsers(),
                    PaginationResponse(10,0)
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                moshi.errorResponse(
                    listOf(e.message ?: "unknown error")
                )
            )
        }
    }
}

fun getUsers() = transaction {
    //    SchemaUtils.create(UsersTable)
    UserEntity.all().limit(10).map {
        it.toUser()
    }
}