package dragos.rachieru.entity

import data.IssueData
import dragos.rachieru.database.IssuesTable
import dragos.rachieru.model.Issue
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

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
class IssueEntity(id: EntityID<Long>) : LongEntity(id), IssueData {

    override val issueId: Long
        get() = id.value

    override var project by ProjectEntity referencedOn IssuesTable.project

    override var creator by UserEntity referencedOn IssuesTable.creator

    override var status by IssuesTable.status

    override var createdAt by IssuesTable.createdAt

    override var updatedAt by IssuesTable.updatedAt

    override var title: String by IssuesTable.title

    override var content: String by IssuesTable.content

    fun toIssue() = Issue(
        issueId, project, creator, status, createdAt, updatedAt, title, content
    )

    companion object : LongEntityClass<IssueEntity>(IssuesTable)
}