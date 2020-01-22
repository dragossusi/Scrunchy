package dragos.rachieru.database

import org.jetbrains.exposed.dao.id.LongIdTable

object AppRolesTable : LongIdTable("app_roles", "_id") {

    val name = varchar("name", 10).uniqueIndex()
    val title = varchar("title", length = 20)

}