package io.scrunchy.server.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import data.UserData
import java.util.*
import java.util.concurrent.TimeUnit

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
object JwtConfig {
    private const val issuer = "github.com/dragossusi"
    private const val secret = "secretdiscretneiubimcumvezinumaiinfilme"
    private val expiresIn = TimeUnit.DAYS.toMillis(3)
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .build()

    /**
     * Produce a token for this combination of User and Password
     */
    fun makeToken(user: UserData): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.userId)
        .withExpiresAt(Date(System.currentTimeMillis() + expiresIn))
        .sign(algorithm)


}