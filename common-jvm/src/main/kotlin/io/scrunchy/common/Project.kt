package io.scrunchy.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import data.ProjectData

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
@JsonClass(generateAdapter = true)
class Project(

    @Json(name = "project_id")
    override val projectId: Long,

    @Json(name = "name")
    override val name: String,

    @Json(name = "description")
    override val description: String?,

    @Json(name = "creator")
    override val creator: User
) : ProjectData {
}