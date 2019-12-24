package app

import tornadofx.*
import view.LoginView

class ScrunchyApp : App(LoginView::class) {
}

fun main(args: Array<String>) {
    launch<ScrunchyApp>(args)
}