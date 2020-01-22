package view

import controller.LoginController
import tornadofx.*
import viewmodel.LoginViewModel

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