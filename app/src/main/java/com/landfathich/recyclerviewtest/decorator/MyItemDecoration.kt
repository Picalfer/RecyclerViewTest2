package com.landfathich.recyclerviewtest.decorator

import android.graphics.Canvas
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.recyclerviewtest.R

class MyItemDecoration : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        parent.children.forEachIndexed{index, view ->
            if (index % 2 == 0) {
                view.setBackgroundResource(R.color.yellow)
            } else {
                view.setBackgroundResource(R.color.blue)
            }
        }
    }
}