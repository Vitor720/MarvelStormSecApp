package com.ddapps.marvelstormsecapp.adapters

import android.view.ViewGroup
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.data.models.Character
import com.ddapps.marvelstormsecapp.di.ktx.inflate
import com.ddapps.marvelstormsecapp.ui.view.BaseViewHolder


class CharacterAdapter : BaseAdapter<Character, BaseViewHolder<Character>>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CharacterViewHolder {
        val itemView = parent.inflate(R.layout.row_character)
        return CharacterViewHolder(itemView)
    }
}