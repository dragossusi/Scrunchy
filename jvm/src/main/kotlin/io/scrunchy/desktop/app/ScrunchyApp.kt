package io.scrunchy.desktop.app

import io.scrunchy.api.client.MoshiModule
import io.scrunchy.api.client.RetrofitModule
import io.scrunchy.desktop.api.Auth
import io.scrunchy.desktop.view.LoginView
import tornadofx.App
import tornadofx.DIContainer
import tornadofx.FX
import tornadofx.launch
import kotlin.reflect.KClass

class ScrunchyApp : App(LoginView::class) {

    init {
        val moshi = MoshiModule.moshi()
        val retrofit = RetrofitModule.retrofit(
            "http://localhost:8080",
            moshi
        )
//        FX.dicontainer = object : DIContainer{
//
//            override fun <T : Any> getInstance(type: KClass<T>): T {
//
//            }
//        }
        FX.getComponents().put(Auth::class, retrofit.create(Auth::class.java))
//        io.scrunchy.desktop.api.engine.requestInterceptor = {
//
//        }
    }

}

fun main(args: Array<String>) {
    launch<ScrunchyApp>(args)
}