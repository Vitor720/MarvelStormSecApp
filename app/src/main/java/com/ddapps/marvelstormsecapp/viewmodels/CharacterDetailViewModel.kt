package com.ddapps.marvelstormsecapp.viewmodels


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.data.MarvelRepository
import com.ddapps.marvelstormsecapp.data.models.DetailData
import com.ddapps.marvelstormsecapp.di.ktx.commonSubscribe
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject


class CharacterDetailViewModel @Inject internal constructor(val repository: MarvelRepository) : ViewModel() {

    val characterComicsLiveData = MutableLiveData<List<DetailData>>()
    val characterEventsLiveData = MutableLiveData<List<DetailData>>()
    val characterStoriesLiveData = MutableLiveData<List<DetailData>>()
    val characterSeriesLiveData = MutableLiveData<List<DetailData>>()
    val errorLiveData = MutableLiveData<Int>()
    private val compositeDisposable = CompositeDisposable()

    companion object {
        const val TAG = "CharDetailViewModel"
        const val RESULTS_SIZE = 3
    }


    fun loadCharacterDetails(characterId: Long) {

        loadCharacterComics(characterId)
        loadCharacterEvents(characterId)
        loadCharacterStories(characterId)
        loadCharacterSeries(characterId)
        Timber.e("Terminou o loadCharacters!")

    }

    private fun loadCharacterSeries(characterId: Long) {
        val disposable = repository.getCharacterSeries(characterId)
            .flatMap { item -> Maybe.just(item.data.results) }
            .commonSubscribe(
                { series -> characterSeriesLiveData.postValue(series.take(RESULTS_SIZE)) },
                { errorLiveData.postValue(R.string.character_detail_loading_error) }
            )
        Timber.e("Terminou o loadCharacters!")

        compositeDisposable.add(disposable)
    }

    private fun loadCharacterStories(characterId: Long) {
        val disposable = repository.getCharacterStories(characterId)
            .flatMap { item -> Maybe.just(item.data.results) }
            .commonSubscribe(
                { stories -> characterStoriesLiveData.postValue(stories.take(RESULTS_SIZE)) },
                { errorLiveData.postValue(R.string.character_detail_loading_error) }
            )

        compositeDisposable.add(disposable)
    }

    private fun loadCharacterEvents(characterId: Long) {
        val disposable = repository.getCharacterEvents(characterId)
            .flatMap { item -> Maybe.just(item.data.results) }
            .commonSubscribe(
                { events -> characterEventsLiveData.postValue(events.take(RESULTS_SIZE)) },
                { errorLiveData.postValue(R.string.character_detail_loading_error) }
            )
        compositeDisposable.add(disposable)
    }

    private fun loadCharacterComics(characterId: Long) {
        val disposable = repository.getCharacterComics(characterId)
            .flatMap { item -> Maybe.just(item.data.results) }
            .commonSubscribe(
                { comics -> characterComicsLiveData.postValue(comics.take(RESULTS_SIZE)) },
                { errorLiveData.postValue(R.string.character_detail_loading_error) }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        Timber.d("onCleared()")
        // clear disposables to avoid memory leaks
        compositeDisposable.clear()
    }
}