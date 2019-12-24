package dragos.rachieru.database

import org.jetbrains.exposed.sql.Table

object UsersTable : Table() {
    val id = long("_id").primaryKey()
    val username = varchar("username",length = 30)
    val password = varchar("password",length = 128)
    val name = varchar("name", length = 50)
//    val role = (long("role") references RolesTable.id).nullable()
}