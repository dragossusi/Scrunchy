package io.scrunchy.api.client

import io.scrunchy.common.BaseDataResponse
import io.scrunchy.common.ListResponse
import io.scrunchy.common.Project
import retrofit2.Response
import retrofit2.http.*

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
interface ProjectsApi {

    @GET("projects")
    suspend fun getProjects(
        @Query("limit") limit: Int? = null,
        @Query("last_id") lastId: String? = null
    ): Response<ListResponse<Project>>

    @FormUrlEncoded
    @POST("projects")
    suspend fun addProject(
        @Field("name") username: String,
        @Field("description") password: String
    ): Response<BaseDataResponse<Project>>

}