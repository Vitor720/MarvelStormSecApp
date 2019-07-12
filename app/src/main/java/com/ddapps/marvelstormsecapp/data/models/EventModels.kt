package com.ddapps.marvelstormsecapp.data.models

data class EventDataWrapper(val code: Int, val status: String, val data: EventDataContainer)
data class EventDataContainer(val results: List<DetailData>)