package io.scrunchy.desktop.view

import io.scrunchy.desktop.controller.LoginController
import tornadofx.*
import io.scrunchy.desktop.viewmodel.LoginViewModel

class LoginView : View() {

    val loginController: LoginController by inject()
    val loginModel = LoginViewModel()

    override val root = form {
        fieldset {
            field("Username") {
                textfield(loginModel.username) {

                }
            }
            field("Password") {
                passwordfield(loginModel.password) {

                }
            }
            field("Remember me") {
                checkbox(property = loginModel.remember)
            }
        }
        button("login") {
            isDefaultButton = true
            action{
                loginModel.commit{
                    loginController.tryLogin(
                        loginModel.username.value,
                        loginModel.password.value,
                        loginModel.remember.value
                    )
                }
            }
        }
    }

    fun clear() {
        loginModel.username.value = ""
        loginModel.password.value = ""
        loginModel.remember.value = false
    }

}