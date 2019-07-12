package com.ddapps.marvelstormsecapp.di.component

import com.ddapps.marvelstormsecapp.PusherAppplication
import com.ddapps.marvelstormsecapp.di.module.BuildFragmentModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        BuildFragmentModule::class]
)
interface AppComponent : AndroidInjector<PusherAppplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PusherAppplication>()
}