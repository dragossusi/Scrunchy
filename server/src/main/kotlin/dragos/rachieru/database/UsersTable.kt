package dragos.rachieru.database

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("users") {
    val id = long("id")
    val username = varchar("username",length = 30).uniqueIndex()
    val password = varchar("password",length = 128)
    val name = varchar("name", length = 50)
    val role = (long("role") references RolesTable.id)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id)

}