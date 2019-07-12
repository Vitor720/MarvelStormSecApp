package com.ddapps.marvelstormsecapp.di.module

import com.ddapps.marvelstormsecapp.ui.heroes.HeroListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHeroListFragment(): HeroListFragment

}
