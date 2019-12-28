package dragos.rachieru.database

import org.jetbrains.exposed.sql.Table


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
object ProjectsTable : Table("projects") {

    val id = long("_id")
    val name = varchar("name",20)
    val description = varchar("description",100).nullable()
    val creatorId= long("creator_id") references UsersTable.id

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id)

}