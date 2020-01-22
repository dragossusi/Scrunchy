package dragos.rachieru.entity

import data.ProjectData
import dragos.rachieru.database.ProjectsTable
import dragos.rachieru.model.Project
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
class ProjectEntity(id: EntityID<Long>) : LongEntity(id), ProjectData {

    override val projectId: Long
        get() = id.value
    override var name: String by ProjectsTable.name
    override var description: String? by ProjectsTable.description
    override var creator by UserEntity referencedOn ProjectsTable.creator

    fun toProject() = Project(
        projectId, name, description, creator
    )

    companion object ProjectDao : LongEntityClass<ProjectEntity>(ProjectsTable)

}