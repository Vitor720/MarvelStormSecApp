package com.ddapps.marvelstormsecapp.ui.view

import android.graphics.Rect
import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView
import android.view.View


class ItemSpacingDecoration(
    @IntRange(from = 0) private var leftOffset: Int = 0,
    @IntRange(from = 0) private var topOffset: Int = 0,
    @IntRange(from = 0) private var rightOffset: Int = 0,
    @IntRange(from = 0) private var bottomOffset: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.set(leftOffset, topOffset, rightOffset, bottomOffset)
    }
}

