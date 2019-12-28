package dragos.rachieru.database

import org.jetbrains.exposed.sql.Table

object RolesTable : Table() {

    val id = long("_id")
    val name = varchar("name", 10).uniqueIndex()
    val title = varchar("title", length = 20)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id)

}