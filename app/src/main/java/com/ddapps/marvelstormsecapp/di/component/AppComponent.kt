package com.ddapps.marvelstormsecapp.di.component

import com.ddapps.marvelstormsecapp.PusherAppplication
import com.ddapps.marvelstormsecapp.di.module.AppModule
import com.ddapps.marvelstormsecapp.di.module.BuildActivityModule
import com.ddapps.marvelstormsecapp.di.module.BuildFragmentModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuildActivityModule::class,
        BuildFragmentModule::class]
)
interface AppComponent : AndroidInjector<PusherAppplication> {
    @Suppress("DEPRECATION")
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PusherAppplication>()
}