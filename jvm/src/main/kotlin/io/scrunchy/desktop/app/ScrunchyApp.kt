package io.scrunchy.desktop.app

import io.scrunchy.api.client.*
import io.scrunchy.desktop.api.AuthDataSource
import io.scrunchy.desktop.api.ConfigTokenSaving
import io.scrunchy.desktop.api.ProjectsDataSource
import io.scrunchy.desktop.view.login.LoginStyle
import io.scrunchy.desktop.view.login.LoginView
import tornadofx.App
import tornadofx.FX
import tornadofx.launch

class ScrunchyApp : App(LoginView::class, LoginStyle::class) {

    init {
        val moshi = MoshiModule.moshi()
        val tokenSaving = ConfigTokenSaving(config)
        val retrofit = retrofit(
            "http://localhost:8080",
            moshi,
            TokenInterceptor(tokenSaving)
        )
//        FX.dicontainer = object : DIContainer{
//
//            override fun <T : Any> getInstance(type: KClass<T>): T {
//
//            }
//        }
        FX.getComponents().put(ConfigTokenSaving::class, tokenSaving)
        FX.getComponents().put(AuthDataSource::class, retrofit.create(AuthDataSource::class.java))
        FX.getComponents().put(ProjectsDataSource::class, retrofit.create(ProjectsDataSource::class.java))
//        io.scrunchy.desktop.api.engine.requestInterceptor = {
//
//        }
    }

}

fun main(args: Array<String>) {
    launch<ScrunchyApp>(args)
}