package dragos.rachieru

import dragos.rachieru.database.RolesTable
import dragos.rachieru.database.UsersTable
import dragos.rachieru.model.BaseResponse
import dragos.rachieru.model.CompletableResponse
import dragos.rachieru.model.User
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.sql.Connection

fun main() {
    val db = Database.connect("jdbc:sqlite:.scrunchy/issues.db", driver = "org.sqlite.JDBC")
    db.transactionManager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE // Or
//     Connection.TRANSACTION_READ_UNCOMMITTED
    transaction(db) {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(RolesTable, UsersTable)
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
        routing {
            post("/register") {
                val user = registerUser(call.receiveParameters())
                call.respond(Json.stringify(BaseResponse.serializer(User.serializer()), BaseResponse.success(user)))
            }
            get("/") {
                try {
                    call.respond(
                        getUsers(db)
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    call.respond(
//                        BaseResponse.serializer()
                        CompletableResponse.error(
                            listOf(e.message ?: "unknown error")
                        )
                    )
                }
            }
            post("/login") {
                call.respond(
                    BaseResponse.success(
                        User(1L, "dragos@mail.com", "Dragos", "Admin")
                    )
                )
            }
        }
    }
    server.start(wait = true)
}

fun registerUser(parameters: Parameters) = transaction {
    UsersTable.insert {
        it[id] = System.currentTimeMillis()
        it[name] = parameters["name"]!!//"Rachieru dragos"
        it[username] = parameters["username"]!!//"dragossusi"
        it[password] = parameters["password"]!!//"123456"
        it[role] = RolesTable.select{ RolesTable.name eq "user"}.single()[RolesTable.id]
    }
}.run {
    User(
        id = this get UsersTable.id,
        username = this get UsersTable.username,
        name = this get UsersTable.name,
        role = "user"
    )
}

fun getUsers(db: Database) = transaction(db) {
    //    SchemaUtils.create(UsersTable)
    (UsersTable innerJoin RolesTable).selectAll().limit(10).map {
        User(
            id = it[UsersTable.id],
            username = it[UsersTable.username],
            name = it[UsersTable.name],
            role = it[RolesTable.title]
        )
    }
}