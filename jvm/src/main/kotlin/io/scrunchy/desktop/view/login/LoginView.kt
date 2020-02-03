package io.scrunchy.desktop.view.login

import io.scrunchy.desktop.controller.LoginController
import io.scrunchy.desktop.view.login.LoginStyle
import io.scrunchy.desktop.viewmodel.LoginViewModel
import javafx.geometry.Orientation
import tornadofx.*

class LoginView : View() {

    val loginController: LoginController by inject()
    val loginModel = LoginViewModel()

    override val root = form {
        addClass(LoginStyle.formClass)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Username", orientation = Orientation.VERTICAL) {
                addClass(LoginStyle.fieldClass)
                textfield(loginModel.username) {
                    addClass(LoginStyle.inputClass)
                }
            }
            field("Password", orientation = Orientation.VERTICAL) {
                addClass(LoginStyle.fieldClass)
                passwordfield(loginModel.password) {
                    addClass(LoginStyle.inputClass)
                }
            }
            field("Remember me") {
                addClass(LoginStyle.fieldClass)
                checkbox(property = loginModel.remember)
            }
        }
        button("Login") {
            addClass(LoginStyle.buttonClass)
            useMaxWidth = true
            isDefaultButton = true
            action {
                loginModel.commit {
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