package io.scrunchy.desktop.controller

import io.scrunchy.desktop.api.AuthDataSource
import io.scrunchy.desktop.api.ConfigTokenSaving
import io.scrunchy.desktop.view.login.LoginView
import io.scrunchy.desktop.view.projects.list.ProjectsView
import kotlinx.coroutines.runBlocking
import tornadofx.Controller
import tornadofx.FX

class LoginController : Controller() {

    val api: AuthDataSource = FX.getComponents().get(AuthDataSource::class) as AuthDataSource
    val tokenSaving: ConfigTokenSaving = FX.getComponents().get(ConfigTokenSaving::class) as ConfigTokenSaving

    val loginView: LoginView by inject()
    val mainView: ProjectsView by inject()

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            runBlocking {
                api.login(username, password)
            }
        } ui { response ->
            if (response.isSuccessful) {
                loginView.clear()

                tokenSaving.accessToken = response.body()!!.data!!
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