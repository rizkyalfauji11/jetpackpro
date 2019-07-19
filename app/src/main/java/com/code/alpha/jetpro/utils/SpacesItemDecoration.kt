package com.code.alpha.jetpro.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space * 2
        } else {
            outRect.top = space
        }

        outRect.left = space
        outRect.right = space
    }
}