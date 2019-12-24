package view

import data.Project
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

class MainView : View() {

    val projects = mutableListOf<Project>().asObservable()

    override val root = hbox {
        vbox {
            imageview("https://i.imgur.com/e8h3Mbc.jpg") {
                fitWidth = 64.0
                fitHeight = 64.0
            }

        }
        vbox {
            text("Sal cf?") {

            }
            listview(projects) {
                cellFormat {
                    text = it.name
                }
            }
        }
        prefWidth = 720.0
        prefHeight = 640.0
    }

}