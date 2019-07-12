package com.ddapps.marvelstormsecapp.data.models

data class Image(val path: String, val extension: String) {
    override fun toString(): String {
        return "$path.$extension"
    }

    fun makeImageXLarge() = "$path/xlarge_landscape.$extension"
}

data class DetailData(val id: Long, val title: String, val description: String?, val thumbnail: Image?)