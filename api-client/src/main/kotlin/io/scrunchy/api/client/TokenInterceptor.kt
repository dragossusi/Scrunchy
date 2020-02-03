package io.scrunchy.api.client

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Scrunchy
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
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
class TokenInterceptor(private val tokenSaving: TokenSaving) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenSaving.accessToken ?: return chain.proceed(chain.request())
        val request = chain.request().newBuilder().apply {
            addHeader("Authorization", "Bearer ${tokenSaving.accessToken}")
        }.build()
        return chain.proceed(request)
    }
}