package dragos.rachieru.database

import org.jetbrains.exposed.sql.Table

object RolesTable : Table() {

    val id = long("_id").primaryKey()
    val title = varchar("name",length = 10)

}