package com.ddapps.marvelstormsecapp.adapters

import android.view.ViewGroup
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.data.models.Character
import com.ddapps.marvelstormsecapp.di.ktx.inflate
import com.ddapps.marvelstormsecapp.ui.view.BaseViewHolder
import timber.log.Timber


class CharacterAdapter : BaseAdapter<Character, BaseViewHolder<Character>>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CharacterViewHolder {
        val itemView = parent.inflate(R.layout.hero_item)
        Timber.e("Chegou no CharacterAdapter!")

        return CharacterViewHolder(itemView)
    }
}