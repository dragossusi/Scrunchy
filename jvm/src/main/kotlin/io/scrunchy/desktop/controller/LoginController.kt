package io.scrunchy.desktop.controller

import io.scrunchy.desktop.api.Auth
import io.scrunchy.desktop.view.LoginView
import io.scrunchy.desktop.view.MainView
import kotlinx.coroutines.runBlocking
import tornadofx.Controller
import tornadofx.FX

class LoginController : Controller() {

    val api: Auth = FX.getComponents().get(Auth::class) as Auth

    val loginView: LoginView by inject()
    val mainView: MainView by inject()

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            runBlocking {
                api.login(username, password)
            }
        } ui { response ->
            if (response.isSuccessful) {
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