package io.scrunchy.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import data.IssueData
import data.ProjectData
import data.UserData

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
class Issue(

    @Json(name="issue_id")
    override val issueId: Long,

    @Json(name="project")
    override val project: ProjectData,

    @Json(name="creator")
    override val creator: UserData,

    @Json(name="status")
    override val status: String,

    @Json(name="created_at")
    override val createdAt: Long,

    @Json(name="updated_at")
    override var updatedAt: Long?,

    @Json(name="title")
    override var title: String,

    @Json(name="content")
    override var content: String
) : IssueData {
}