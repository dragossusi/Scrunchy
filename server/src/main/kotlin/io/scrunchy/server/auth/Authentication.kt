package io.scrunchy.server.auth

import io.scrunchy.server.entity.UserEntity
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import io.ktor.auth.principal
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


fun Application.installAuth() {
    install(Authentication) {
        jwt {
            realm = "scrunchy"
            verifier {
                JwtConfig.verifier
            }
            validate {
                val id = it.payload.getClaim("id")?.asLong()
                id?.let {
                    transaction {
                        UserEntity.findById(it)
                    }
                }
            }
        }
    }
}

val ApplicationCall.user
    get() = principal<UserEntity>()


const val AUTH_USER = "userauth"