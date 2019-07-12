package com.ddapps.marvelstormsecapp.di.module

import android.app.Application
import android.content.Context
import com.ddapps.marvelstormsecapp.PusherAppplication

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: PusherAppplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideApplication(application: PusherAppplication): Application {
        return application
    }
}