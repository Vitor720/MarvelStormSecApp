package com.ddapps.marvelstormsecapp.data.models

data class StoryDataWrapper(val code: Int, val status: String, val data: StoryDataContainer)
data class StoryDataContainer(val results: List<DetailData>)