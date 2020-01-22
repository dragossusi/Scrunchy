package dragos.rachieru.database

import org.jetbrains.exposed.dao.id.LongIdTable

object UsersTable : LongIdTable("users", "_id") {
    val username = varchar("username", length = 30).uniqueIndex()
    val password = varchar("password", length = 128)
    val name = varchar("name", length = 50)
    val role = reference("role", AppRolesTable)
}