package app

import tornadofx.*
import view.LoginView

class ScrunchyApp : App(LoginView::class) {

    val api: Rest by inject()

    init {
        api.baseURI = "http://teamp.go.ro:8080"
//        api.engine.requestInterceptor = {
//
//        }
    }

}

fun main(args: Array<String>) {
    launch<ScrunchyApp>(args)
}