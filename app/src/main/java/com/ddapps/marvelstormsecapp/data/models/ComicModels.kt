package com.ddapps.marvelstormsecapp.data.models

data class ComicDataWrapper(val code: Int, val status: String, val data: ComicDataContainer)
data class ComicDataContainer(val results: List<DetailData>)