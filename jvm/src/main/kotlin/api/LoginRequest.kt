package api

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

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
class LoginRequest(username:String,password:String) : JsonModel {

    val userNameProperty = SimpleStringProperty(username)
    var userName by userNameProperty

    val passwordProperty = SimpleStringProperty(password)
    var password by userNameProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            userName = string("username")
            password = string("password")
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("username", userName)
            add("password", password)
        }
    }

}