package io.scrunchy.desktop.controller

import io.scrunchy.api.client.ProjectsApi
import io.scrunchy.desktop.api.ProjectsDataSource
import io.scrunchy.desktop.view.projects.add.AddProjectView
import kotlinx.coroutines.runBlocking
import tornadofx.Controller
import tornadofx.FX

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
class AddProjectController : Controller() {

    val addProjectView: AddProjectView by inject()
    val api: ProjectsApi = FX.getComponents().get(ProjectsDataSource::class) as ProjectsDataSource

    fun addProject(name: String, description: String) {
        runAsync {
            runBlocking {
                api.addProject(name, description)
            }
        } ui { response ->
            if (response.isSuccessful) {
                addProjectView.close()
            } else {
                println("nu merse!")
                throw Error(response.errorBody()?.string())
            }
        }
    }

}