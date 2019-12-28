package dragos.rachieru.mapper

import data.Project
import dragos.rachieru.database.ProjectsTable
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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */

fun ResultRow.toProject(): Project {
    return Project(
        id = get(ProjectsTable.id),
        name = get(ProjectsTable.name),
        description = getOrNull(ProjectsTable.description),
        creator_id = get(ProjectsTable.creatorId)
    )
}