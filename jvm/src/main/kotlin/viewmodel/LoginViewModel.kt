package viewmodel

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

class LoginViewModel : ViewModel() {

    val username = bind { SimpleStringProperty() }
    val password = bind { SimpleStringProperty() }
    val remember = bind{ SimpleBooleanProperty() }

}