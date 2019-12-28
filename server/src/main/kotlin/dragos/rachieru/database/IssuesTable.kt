package dragos.rachieru.database

import org.jetbrains.exposed.sql.Table

object IssuesTable : Table("issues"){

    val id = long("_id")
    val projectId = long("project_id") references ProjectsTable.id
    val creatorId = long("creator_id") references UsersTable.id
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()
    val status = varchar("status",15)
    val title = varchar("title",length = 30)
    val content = varchar("content",500)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id)

}