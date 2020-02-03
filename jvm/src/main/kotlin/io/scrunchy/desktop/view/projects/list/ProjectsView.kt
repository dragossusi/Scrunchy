package io.scrunchy.desktop.view.projects.list

import io.scrunchy.common.Project
import io.scrunchy.desktop.controller.ProjectsController
import io.scrunchy.desktop.view.projects.add.AddProjectView
import io.scrunchy.desktop.viewmodel.LoginViewModel
import javafx.scene.Parent
import tornadofx.*

/**
 * Scrunchy
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * Scrunchy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Scrunchy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Scrunchy.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */
class ProjectsView : View() {

    val loginController: ProjectsController by inject()
    val loginModel = LoginViewModel()

    override fun onCreate() {
        super.onCreate()
        loginController.getProjects()
    }

    fun setProjects(projects: List<Project>) {
        loginModel.projects.value = projects.asObservable()
    }

    override val root: Parent = vbox {
        useMaxWidth = true
        hbox {
            useMaxWidth = true
            button("menu") {
                action {
                    loginController.getProjects()
                }
            }
            button("add") {
                action {
                    openInternalWindow<AddProjectView>()
                }
            }
        }
        vbox {
            useMaxWidth = true
            listview(loginModel.projects) {
                cellFormat {
                    text = it.name
                }
            }
        }
    }
}