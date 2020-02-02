package io.scrunchy.server.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.scrunchy.common.BaseCompletableResponse
import io.scrunchy.common.BaseDataResponse
import io.scrunchy.common.ListResponse
import io.scrunchy.common.PaginationResponse

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

inline fun <reified T> Moshi.dataResponse(data: T): String {
    return adapter<BaseDataResponse<T>>(Types.newParameterizedType(BaseDataResponse::class.java, T::class.java))
        .toJson(BaseDataResponse.success(data))
}

fun Moshi.errorResponse(errors: List<String>): String {
    return adapter<BaseCompletableResponse>(BaseCompletableResponse::class.java)
        .toJson(BaseCompletableResponse.error(errors))
}

inline fun <reified T> Moshi.listDataResponse(list: List<T>, pagination: PaginationResponse): String {
    return adapter<ListResponse<T>>(
        Types.newParameterizedType(
            ListResponse::class.java,
            Types.newParameterizedType(
                List::class.java,
                T::class.java
            )
        )
    ).toJson(ListResponse.success(list, pagination))
}