package io.scrunchy.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
@JsonClass(generateAdapter = false)
class ListResponse<T>(
    @Json(name = "response_type")
    responseType: ResponseType,
    @Json(name = "data")
    data: List<T>?,
    @Json(name = "pagination")
    val pagination: PaginationResponse?,
    @Json(name = "errors")
    errors: List<String>?
) : BaseDataResponse<List<T>>(responseType, data, errors) {

    companion object {
        fun <T> error(errors: List<String>?) =
            ListResponse<T>(ResponseType.ERROR, null, null, errors)

        fun <T> success(data: List<T>, pagination: PaginationResponse) =
            ListResponse(ResponseType.SUCCESS, data, pagination, null)
    }

}