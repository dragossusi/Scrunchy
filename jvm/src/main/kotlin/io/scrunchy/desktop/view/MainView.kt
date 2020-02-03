package io.scrunchy.desktop.view

import data.ProjectData
import io.scrunchy.common.AppRole
import io.scrunchy.common.Project
import io.scrunchy.common.User
import tornadofx.*

class MainView : View() {

    val projects = mutableListOf<ProjectData>(
        Project(
            System.currentTimeMillis(),
            "asdfg",
            "asdfg",
            User(
                System.currentTimeMillis(),
                "dragossusi",
                "Rachieru Dragos",
                AppRole(
                    System.currentTimeMillis(),
                    "admin",
                    "admin"
                )
            )
        )
    ).asObservable()

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
        }
        prefWidth = 720.0
        prefHeight = 640.0
    }

}