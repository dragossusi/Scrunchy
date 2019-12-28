package dragos.rachieru.routing

import dragos.rachieru.database.RolesTable
import dragos.rachieru.database.UsersTable
import dragos.rachieru.model.CompletableResponse
import dragos.rachieru.model.User
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
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

fun Route.routeUsers() {
    get("/users") {
        try {
            call.respond(
                getUsers()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
//                        BaseResponse.serializer()
                CompletableResponse.error(
                    listOf(e.message ?: "unknown error")
                )
            )
        }
    }
}

fun getUsers() = transaction {
    //    SchemaUtils.create(UsersTable)
    (UsersTable innerJoin RolesTable).selectAll().limit(10).map {
        User(
            id = it[UsersTable.id],
            username = it[UsersTable.username],
            name = it[UsersTable.name],
            role = it[RolesTable.title]
        )
    }
}