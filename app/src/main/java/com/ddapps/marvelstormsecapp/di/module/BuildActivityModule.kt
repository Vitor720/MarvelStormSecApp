package com.ddapps.marvelstormsecapp.di.module

import com.ddapps.marvelstormsecapp.MainActivity
import com.ddapps.marvelstormsecapp.ui.CharacterDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildActivityModule {
    @ContributesAndroidInjector(modules = [BuildFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailActivity(): CharacterDetailActivity
}