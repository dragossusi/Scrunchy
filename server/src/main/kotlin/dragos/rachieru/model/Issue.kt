package dragos.rachieru.model

import data.IssueData
import data.ProjectData
import data.UserData
import io.ktor.util.date.GMTDate
import kotlinx.serialization.ContextualSerialization
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
class Issue(
    override val issueId: Long,
    override val project: ProjectData,
    override val creator: UserData,
    override val status: String,
    override val createdAt: Long,
    override var updatedAt: Long?,
    override var title: String,
    override var content: String
) : IssueData {
}