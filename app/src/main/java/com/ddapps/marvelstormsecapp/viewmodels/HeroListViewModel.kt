package com.ddapps.marvelstormsecapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.data.MarvelRepository
import com.ddapps.marvelstormsecapp.data.models.Character
import com.ddapps.marvelstormsecapp.di.ktx.commonSubscribe
import com.malinskiy.superrecyclerview.OnMoreListener
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HeroListViewModel @Inject internal constructor(private val repository: MarvelRepository) : ViewModel(),
    OnMoreListener {

    val allHeroesLiveData = MutableLiveData<List<Character>>()
    val searchResultsLiveData = MutableLiveData<List<Character>>()
    val errorLiveData = MutableLiveData<Int>()
    private val compositeDisposable = CompositeDisposable()
    var currentOffset: Int = 0

    var isSearching: Boolean = false

    companion object {
        const val TAG = "HeroListViewModel"
        const val RESULTS_OFFSET = 20
    }

    fun loadHeroes(offset: Int = 0) {
        val disposable = repository.getAllCharacters(offset)
            .flatMap { item -> Maybe.just(item.data.results) }
            .commonSubscribe(
                { characters -> allHeroesLiveData.postValue(characters) },
                { errorLiveData.postValue(R.string.network_request_error_load) }
            )
        compositeDisposable.add(disposable)
    }

    fun searchHeroes(query: String) {
        currentOffset = 0
        if (query.isNotEmpty()) {
            val disposable = repository.getCharactersByName(query)
                .toObservable()
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { query.length > 5 }
                .flatMap { item -> Observable.just(item.data.results) }

                .commonSubscribe(
                    { characters -> searchResultsLiveData.postValue(characters) },


                    {
                        errorLiveData.postValue(R.string.network_request_error)
                    }
                )

            compositeDisposable.add(disposable)
        } else {
            isSearching = false
            loadHeroes()
        }
    }

    override fun onMoreAsked(overallItemsCount: Int, itemsBeforeMore: Int, maxLastVisiblePosition: Int) {
        if (itemsBeforeMore + maxLastVisiblePosition >= overallItemsCount) {
            currentOffset = currentOffset.plus(RESULTS_OFFSET)
            loadHeroes(currentOffset)
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}