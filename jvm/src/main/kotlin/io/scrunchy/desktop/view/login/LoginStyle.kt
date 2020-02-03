package io.scrunchy.desktop.view.login

import javafx.scene.paint.Color
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

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
class LoginStyle : Stylesheet() {

    companion object {
        val formClass by cssclass()
        val fieldClass by cssclass()
        val inputClass by cssclass()
        val buttonClass by cssclass()

        private val background = Color.BLACK
        private val inputBackground = Color.WHITE
        private val buttonBackground = Color.DARKGREEN
    }

    init {
        formClass {
            backgroundColor += background
        }
        fieldClass {
            label{
                textFill = Color.WHITE
            }
        }
        inputClass {
            borderRadius += box(10.px)
            backgroundRadius += box(10.px)
            backgroundColor += inputBackground
        }
        buttonClass {
            borderRadius += box(10.px)
            backgroundColor += buttonBackground
            backgroundRadius += box(10.px)
            textFill = Color.WHITE
            pressed {
                backgroundColor += Color.LIGHTGREEN
            }
        }
    }

}