package com.ddapps.marvelstormsecapp.di.module


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ddapps.marvelstormsecapp.di.annotation.ViewModelKey
import com.ddapps.marvelstormsecapp.viewmodels.CharacterDetailViewModel
import com.ddapps.marvelstormsecapp.viewmodels.HeroListViewModel
import com.ddapps.marvelstormsecapp.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HeroListViewModel::class)
    abstract fun bindHeroListViewModel(heroListViewModel: HeroListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModel(characterDetailViewModel: CharacterDetailViewModel): ViewModel
}