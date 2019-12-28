package dragos.rachieru.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
@Serializable
class ListResponse<T>(
    @SerialName("responseType")
    val responseType: ResponseType,
    @SerialName("data")
    val data: List<T>?,
    @SerialName("pagination")
    val pagination: PaginationResponse?,
    @SerialName("errors")
    val errors: List<String>?
) {

    companion object {
        fun <T> error(errors: List<String>?) = ListResponse<T>(ResponseType.ERROR, null, null, errors)
        fun <T> success(data: List<T>, pagination: PaginationResponse) = ListResponse(ResponseType.SUCCESS, data, pagination, null)
    }

}