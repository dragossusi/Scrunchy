package io.scrunchy.server.database

import org.jetbrains.exposed.dao.id.LongIdTable

object IssuesTable : LongIdTable("issues","_id"){

    val project= reference("project_id", ProjectsTable)
    val creator = reference("creator", UsersTable)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()
    val status = varchar("status",15)
    val title = varchar("title",length = 30)
    val content = varchar("content",500)

}