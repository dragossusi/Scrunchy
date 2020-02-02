package io.scrunchy.server

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.auth.authenticate
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.scrunchy.server.database.AppRolesTable
import io.scrunchy.server.database.UsersTable
import io.scrunchy.server.auth.installAuth
import io.scrunchy.server.database.IssuesTable
import io.scrunchy.server.database.ProjectsTable
import io.scrunchy.server.entity.AppRoleEntity
import io.scrunchy.server.routing.routeAuth
import io.scrunchy.server.routing.routeProjects
import io.scrunchy.server.routing.routeUsers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.sql.Connection
import java.util.*

fun main() {
//    val db = Database.connect("jdbc:sqlite:.scrunchy/issues.db", driver = "org.sqlite.JDBC")
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
    val db = Database.connect(
        "jdbc:mysql://marinescu.xyz:3306/scrunchy", driver = "com.mysql.cj.jdbc.Driver",
        user = "dragos", password = "Dragos!@#$"
    )
    db.transactionManager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE // Or
//     Connection.TRANSACTION_READ_UNCOMMITTED
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(AppRolesTable)
        SchemaUtils.create(UsersTable)
        SchemaUtils.create(ProjectsTable)
        SchemaUtils.create(IssuesTable)
    }
    try {
        transaction {
            AppRoleEntity.new {
                name = "owner"
                title = "Owner"
            }
            AppRoleEntity.new {
                name = "admin"
                title = "Administrator"
            }
            AppRoleEntity.new {
                name = "user"
                title = "User"
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    val server = embeddedServer(Netty, port = 8080) {
        installAuth()
        routing {
            routeAuth(moshi)
            authenticate {
                routeUsers(moshi)
                routeProjects(moshi)
            }
        }
    }
    server.start(wait = true)
}