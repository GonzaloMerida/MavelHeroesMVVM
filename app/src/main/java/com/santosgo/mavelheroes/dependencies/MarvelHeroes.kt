package com.santosgo.mavelheroes.dependencies

import android.app.Application

class MarvelHeroes : Application() {

    private lateinit var _appContainer : AppContainer
    val appContainer get() = _appContainer

    override fun onCreate() {
        super.onCreate()
        _appContainer = AppContainer(this)
    }
}