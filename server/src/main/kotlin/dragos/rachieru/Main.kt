package dragos.rachieru

import dragos.rachieru.database.UsersTable
import dragos.rachieru.model.BaseResponse
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
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

fun main(args: Array<String>) {
    Database.connect("jdbc:sqlite:.scrunchy/issues.db", driver = "org.sqlite.JDBC")
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            serialization()
        }
        routing {
            post("/register") {
                val user = registerUser(call.receiveParameters())
                call.respond("")
            }
            get("/") {
                try {
                    call.respond(
                        getUsers()
                    )
                }catch (e:Exception) {
                    call.respond(
                        BaseResponse(
                            BaseResponse.ResponseType.ERROR,
                            null,
                            listOf(e.message?:"unknown error")
                        )
                    )
                }
            }
            post("/login") {
                call.respond(
                    BaseResponse(
                        BaseResponse.ResponseType.SUCCESS,
                        User(1L,"dragos@mail.com", "Dragos", "Admin"),
                        null
                    )
                )
            }
        }
    }
    server.start(wait = true)
}

fun registerUser(parameters: Parameters) = transaction {
    SchemaUtils.create(UsersTable)
    UsersTable.insert {
        it[id] = System.currentTimeMillis()
        it[name] = parameters["name"]!!//"Rachieru dragos"
        it[username] = parameters["username"]!!//"dragossusi"
        it[password] = parameters["password"]!!//"123456"
    }
}.run {
    User(
        id = this get UsersTable.id,
        username = this get UsersTable.username,
        name = this get UsersTable.name,
        role = "No role yet"
    )
}

fun getUsers() = transaction {
    SchemaUtils.create(UsersTable)
    UsersTable.selectAll()
}.let{
    it.map {
        User(
            id = it[UsersTable.id],
            username = it[UsersTable.username],
            name = it[UsersTable.name],
            role = "No role yet"
        )
    }
}