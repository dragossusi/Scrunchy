package io.scrunchy.server.entity

import data.UserData
import io.scrunchy.server.database.UsersTable
import io.scrunchy.common.User
import io.ktor.auth.Principal
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
class UserEntity(id: EntityID<Long>) : LongEntity(id), UserData, Principal {

    override val userId: Long
        get() = id.value
    override var username: String by UsersTable.username
    var password: String by UsersTable.password
    override var name: String by UsersTable.name
    override var role by AppRoleEntity referencedOn UsersTable.role

    fun toUser(): User = User(
        userId, username, name, role.toAppRole()
    )

    companion object UserDao : LongEntityClass<UserEntity>(
        UsersTable
    )

}