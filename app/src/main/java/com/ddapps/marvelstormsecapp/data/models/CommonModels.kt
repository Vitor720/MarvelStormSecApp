package com.ddapps.marvelstormsecapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Image(val path: String, val extension: String) {
    override fun toString(): String {
        return "$path/portrait_uncanny.$extension"
    }

    fun setImageToPortrait() = "$path/portrait_small.$extension"
    fun setImageToStandard() = "$path/standard_xlarge.$extension"
    fun setImageFullRes() = "$path.$extension"
}


data class DetailData(
    val id: Long,
    val title: String,
    val description: String?,
    val thumbnail: Image?
)

@Parcelize
data class FavoriteHero(val id: Long, val name: String, val description: String?, val thumbnail: String) : Parcelable