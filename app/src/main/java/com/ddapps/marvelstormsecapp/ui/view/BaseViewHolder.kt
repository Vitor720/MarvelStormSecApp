package com.ddapps.marvelstormsecapp.ui.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected val context: Context = itemView.context
    protected val resources = context.resources
    abstract fun bind(item: T)
}