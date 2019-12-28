package dragos.rachieru.mapper

import data.IssueData
import dragos.rachieru.database.IssuesTable
import dragos.rachieru.model.Issue
import io.ktor.util.date.GMTDate
import org.jetbrains.exposed.sql.ResultRow

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

fun ResultRow.toIssue(): Issue {
    return Issue(
        id = get(IssuesTable.id),
        projectId = get(IssuesTable.projectId),
        userId = get(IssuesTable.creatorId),
        status = IssueData.Status.valueOf(get(IssuesTable.status)),
        createdAt = GMTDate(get(IssuesTable.createdAt)),
        updatedAt = GMTDate(get(IssuesTable.updatedAt)),
        title = get(IssuesTable.title),
        content = get(IssuesTable.content)
    )
}