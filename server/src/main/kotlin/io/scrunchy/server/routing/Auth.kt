package io.scrunchy.server.routing

import io.scrunchy.server.auth.JwtConfig
import io.scrunchy.server.database.AppRolesTable
import io.scrunchy.server.database.UsersTable
import io.scrunchy.server.entity.AppRoleEntity
import io.scrunchy.server.entity.UserEntity
import io.scrunchy.common.BaseDataResponse
import io.scrunchy.common.BaseCompletableResponse
import io.scrunchy.common.User
import io.ktor.application.call
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.jetbrains.exposed.sql.and
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
fun Route.routeAuth() {
    post("/register") {
        val user = registerUser(call.receiveParameters())
        user?.let {
            call.respond(Json.stringify(BaseDataResponse.serializer(User.serializer()), BaseDataResponse.success(it)))
        } ?: call.respond(BaseCompletableResponse.error(listOf("cant register")))
    }
    post("/login") {
        val params = call.parameters
        val userName = params["username"]!!
        val password = params["password"]!!
        val user = transaction {
            UserEntity.find {
                (UsersTable.username eq userName) and (UsersTable.password eq password)
            }.single()
        }

        call.respond(
            Json.stringify(
                BaseDataResponse.serializer(String.serializer()),
                BaseDataResponse.success(
                    JwtConfig.makeToken(user)
                )
            )
        )
    }
}

fun registerUser(parameters: Parameters): User? = transaction {
    UserEntity.new {
        name = parameters["name"]!!//"Rachieru dragos"
        username = parameters["username"]!!//"dragossusi"
        password = parameters["password"]!!//"123456"
        role = AppRoleEntity.find { AppRolesTable.name eq "user" }.single()
    }.toUser()
}