package dragos.rachieru

import dragos.rachieru.auth.installAuth
import dragos.rachieru.database.IssuesTable
import dragos.rachieru.database.ProjectsTable
import dragos.rachieru.database.RolesTable
import dragos.rachieru.database.UsersTable
import dragos.rachieru.routing.routeAuth
import dragos.rachieru.routing.routeIssues
import dragos.rachieru.routing.routeProjects
import dragos.rachieru.routing.routeUsers
import io.ktor.application.install
import io.ktor.auth.authenticate
import io.ktor.features.ContentNegotiation
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.UnstableDefault
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.sql.Connection

@UnstableDefault
fun main() {
//    val db = Database.connect("jdbc:sqlite:.scrunchy/issues.db", driver = "org.sqlite.JDBC")
    val db = Database.connect("jdbc:mysql://marinescu.xyz:3306/scrunchy", driver = "com.mysql.cj.jdbc.Driver",
        user = "dragos",password = "Dragos!@#$")
    db.transactionManager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE // Or
//     Connection.TRANSACTION_READ_UNCOMMITTED
    transaction(db) {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(RolesTable, UsersTable,ProjectsTable,IssuesTable)
        RolesTable.insertIgnore {
            it[id] = System.currentTimeMillis()
            it[name] = "owner"
            it[title] = "Owner"
        }
        RolesTable.insertIgnore {
            it[id] = System.currentTimeMillis()
            it[name] = "admin"
            it[title] = "Administrator"
        }
        RolesTable.insertIgnore {
            it[id] = System.currentTimeMillis()
            it[name] = "user"
            it[title] = "User"
        }
    }
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            serialization()
        }
        installAuth()
        routing {
            routeAuth()
            authenticate {
                routeUsers()
                routeProjects()
                routeIssues()
            }
        }
    }
    server.start(wait = true)
}