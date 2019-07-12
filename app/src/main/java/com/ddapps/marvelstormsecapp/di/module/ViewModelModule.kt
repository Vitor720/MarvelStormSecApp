package com.ddapps.marvelstormsecapp.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ddapps.marvelstormsecapp.viewmodels.ViewModelFactory
import com.ddapps.marvelstormsecapp.di.annotation.ViewModelKey
import com.ddapps.marvelstormsecapp.ui.heroes.HeroListViewModel
import com.ddapps.marvelstormsecapp.ui.detail.CharacterDetailViewModel
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