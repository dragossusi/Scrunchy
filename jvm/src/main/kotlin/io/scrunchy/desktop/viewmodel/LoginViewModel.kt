package io.scrunchy.desktop.viewmodel

import io.scrunchy.common.Project
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

class LoginViewModel : ViewModel() {

    val username = bind { SimpleStringProperty() }
    val password = bind { SimpleStringProperty() }
    val remember = bind { SimpleBooleanProperty() }

    val projects = bind { SimpleListProperty<Project>() }

}