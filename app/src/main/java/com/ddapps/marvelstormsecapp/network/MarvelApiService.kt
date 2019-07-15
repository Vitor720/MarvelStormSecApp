package com.ddapps.marvelstormsecapp.network

import com.ddapps.marvelstormsecapp.data.models.*
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApiService {

    @GET("/v1/public/characters")
    fun getAllCharacters(@Query("offset") offset: Int = 0): Maybe<CharacterDataWrapper>

    @GET("/v1/public/characters")
    fun getCharactersByName(@Query("nameStartsWith") query: String): Maybe<CharacterDataWrapper>

    @GET("/v1/public/characters/{character_id}/comics")
    fun getCharacterComics(@Path("character_id") characterId: Long): Maybe<ComicDataWrapper>

    @GET("/v1/public/characters/{character_id}/events")
    fun getCharacterEvents(@Path("character_id") characterId: Long): Maybe<EventDataWrapper>

    @GET("/v1/public/characters/{character_id}/stories")
    fun getCharacterStories(@Path("character_id") characterId: Long): Maybe<StoryDataWrapper>

    @GET("/v1/public/characters/{character_id}/series")
    fun getCharacterSeries(@Path("character_id") characterId: Long): Maybe<SeriesDataWrapper>
}