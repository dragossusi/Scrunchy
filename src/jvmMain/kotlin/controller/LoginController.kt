package controller

import tornadofx.Controller
import view.LoginView

class LoginController : Controller() {

    val loginView:LoginView by inject()

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            username == "admin" && password == "secret"
        } ui { successfulLogin ->
            if (successfulLogin) {
                loginView.clear()

                if (remember) {
                    with(config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }
                TODO()
            } else {
                TODO()
            }
        }
    }

}

const val USERNAME = "username"
const val PASSWORD = "password"