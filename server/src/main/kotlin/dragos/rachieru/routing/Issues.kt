package dragos.rachieru.routing

import dragos.rachieru.model.BaseResponse
import dragos.rachieru.model.User
import io.ktor.application.call
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import kotlinx.serialization.json.Json

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

fun Route.routeIssues() {

    get("/issues/{project_id}") {
        val user = registerUser(call.receiveParameters())
        call.respond(Json.stringify(BaseResponse.serializer(User.serializer()), BaseResponse.success(user)))
    }
    post("/issues") {
        call.respond(
            BaseResponse.success(
                User(1L, "dragos@mail.com", "Dragos", "Admin")
            )
        )
    }
}