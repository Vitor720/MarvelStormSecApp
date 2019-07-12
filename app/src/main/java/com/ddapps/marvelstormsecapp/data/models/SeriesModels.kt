package com.ddapps.marvelstormsecapp.data.models

data class SeriesDataWrapper(val code: Int, val status: String, val data: SeriesDataContainer)
data class SeriesDataContainer(val results: List<DetailData>)