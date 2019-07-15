package com.ddapps.marvelstormsecapp.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.data.models.DetailData
import com.ddapps.marvelstormsecapp.di.ktx.inflate
import com.ddapps.marvelstormsecapp.di.ktx.load
import com.ddapps.marvelstormsecapp.di.ktx.show
import com.ddapps.marvelstormsecapp.di.ktx.toTypeface
import com.ddapps.marvelstormsecapp.ui.view.BaseViewHolder
import kotlinx.android.synthetic.main.detail_data_item.view.*

class DetailDataAdapter : BaseAdapter<DetailData, DetailDataAdapter.DetailDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailDataViewHolder {
        val itemView = parent.inflate(R.layout.detail_data_item)
        return DetailDataViewHolder(itemView)
    }

    inner class DetailDataViewHolder(itemView: View) : BaseViewHolder<DetailData>(itemView) {
        override fun bind(item: DetailData) {
            itemView.apply {

                detail_data_title.text = item.title

                detail_data_description.text = if (item.description == null || item.description.isEmpty()) {
                    context.getString(R.string.detail_data_description_empty)
                } else {
                    item.description
                }

                item.thumbnail?.let {
                    detail_data_image.show()
                    detail_data_image.load(itemView.context as Activity, item.thumbnail.setImageToPortrait())
                }

                detail_data_title.toTypeface("OpenSans-Regular")
                detail_data_description.toTypeface("OpenSans-Light")
            }
        }
    }
}