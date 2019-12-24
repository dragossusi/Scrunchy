package dragos.rachieru

import dragos.rachieru.model.BaseResponse
import dragos.rachieru.model.User
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            serialization()
        }
        routing {
            get("/") {
                call.respond(
                    User("dragos@mail.com", "Dragos", "Admin")
                )
            }
            post("/login") {
                call.respond(
                    BaseResponse(
                        BaseResponse.ResponseType.SUCCESS,
                        User("dragos@mail.com", "Dragos", "Admin"),
                        null
                    )
                )
            }
        }
    }
    server.start(wait = true)
}