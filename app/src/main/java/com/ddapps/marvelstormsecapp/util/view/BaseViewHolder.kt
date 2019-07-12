package com.ddapps.marvelstormsecapp.util.view

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected val context: Context = itemView.context
    protected val resources = context.resources
    abstract fun bind(item: T)
}