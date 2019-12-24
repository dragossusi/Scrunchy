package controller

import tornadofx.Controller
import view.LoginView
import view.MainView

class LoginController : Controller() {

    val loginView: LoginView by inject()
    val mainView: MainView by inject()

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            username == "dragos@mail.com" && password == "123456"
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
                showMainView()
            } else {
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