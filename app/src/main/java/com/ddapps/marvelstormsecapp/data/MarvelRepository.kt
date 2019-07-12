package com.ddapps.marvelstormsecapp.data

import com.ddapps.marvelstormsecapp.data.models.*
import com.ddapps.marvelstormsecapp.network.MarvelApiService
import io.reactivex.Maybe
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val marvelApi: MarvelApiService) {

    fun getAllCharacters(offset: Int): Maybe<CharacterDataWrapper> {
        return marvelApi.getAllCharacters(offset)
    }

    fun getCharactersByName(query: String): Maybe<CharacterDataWrapper> {
        return marvelApi.getCharactersByName(query)
    }

    fun getCharacterComics(characterId: Long): Maybe<ComicDataWrapper> {
        return marvelApi.getCharacterComics(characterId)
    }

    fun getCharacterEvents(characterId: Long): Maybe<EventDataWrapper> {
        return marvelApi.getCharacterEvents(characterId)
    }

    fun getCharacterStories(characterId: Long): Maybe<StoryDataWrapper> {
        return marvelApi.getCharacterStories(characterId)
    }

    fun getCharacterSeries(characterId: Long): Maybe<SeriesDataWrapper> {
        return marvelApi.getCharacterSeries(characterId)
    }
}