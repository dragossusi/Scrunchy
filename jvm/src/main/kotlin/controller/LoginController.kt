package controller

import api.LoginRequest
import tornadofx.Controller
import tornadofx.Rest
import view.LoginView
import view.MainView

class LoginController : Controller() {

    val api: Rest by inject()

    val loginView: LoginView by inject()
    val mainView: MainView by inject()

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            api.post("login",LoginRequest(username,password))
        } ui { response ->
            if (response.ok()) {
                loginView.clear()

                if (remember) {
                    with(config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }
                showMainView()
            } else {
                println("nu merse!")
                TODO()
            }
        }
    }

    fun showMainView() {
        loginView.replaceWith(mainView, centerOnScreen = true, sizeToScene = true)
    }

}

const val USERNAME = "username"
const val PASSWORD = "password"