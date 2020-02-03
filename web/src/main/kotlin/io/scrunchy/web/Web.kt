package io.scrunchy.web

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*

/**
 * Scrunchy
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * Scrunchy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Scrunchy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Scrunchy.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */
fun main() {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get {
                call.respondHtml {
                    head {
                        title {
                            +"Scrunchy"
                        }
                        link(rel = "stylesheet", href = "css/login.css", type = "text/css")
                    }
                    body {
                        form(classes = "login-form") {
                            input(InputType.text) {
                                placeholder = "username"
                            }
                            input(InputType.password) {
                                placeholder = "password"
                            }
                            button {
                                +"Login"
                            }
                            p("message") {
                                +"Not registered? "
                                a(href = "#") {
                                    +"Create an account"
                                }
                            }
                        }
                    }
                }
            }
            static("css") {
                resources("css")
            }
        }
    }
    server.start(wait = true)
}