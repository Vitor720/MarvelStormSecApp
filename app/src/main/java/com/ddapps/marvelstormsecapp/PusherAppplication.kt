package com.ddapps.marvelstormsecapp

import com.ddapps.marvelstormsecapp.di.Injector
import com.ddapps.marvelstormsecapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class PusherAppplication : DaggerApplication() {

    companion object {
        var instance: PusherAppplication? = null
            private set
    }

    override fun applicationInjector(): AndroidInjector<PusherAppplication> =
        DaggerAppComponent.builder().create(this@PusherAppplication)

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        instance = this
    }
}
