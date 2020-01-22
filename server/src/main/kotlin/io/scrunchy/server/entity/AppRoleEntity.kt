package io.scrunchy.server.entity

import data.AppRoleData
import io.scrunchy.server.database.AppRolesTable
import io.scrunchy.common.AppRole
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
class AppRoleEntity(id: EntityID<Long>) : LongEntity(id), AppRoleData {

    override val roleId: Long
        get() = id.value
    override var name: String by AppRolesTable.name
    override var title: String by AppRolesTable.title

    companion object AppRoleDao : LongEntityClass<AppRoleEntity>(
        AppRolesTable
    )


    fun toAppRole() = AppRole(roleId, name, title)

}