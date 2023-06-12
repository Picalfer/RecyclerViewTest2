package com.skill_factory.unit3.animator

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.recyclerviewtest.R

class MyItemAnimator(private val context: Context) : DefaultItemAnimator() {
    val animAdd = AnimationUtils.loadAnimation(context, R.anim.slide_from_right)
    val animRemove = AnimationUtils.loadAnimation(context, R.anim.slide_to_right)

    override fun onAddStarting(item: RecyclerView.ViewHolder?) {
        item?.itemView?.startAnimation(animAdd)
    }

    override fun getAddDuration(): Long {
        return animAdd.duration
    }

    override fun onRemoveStarting(item: RecyclerView.ViewHolder?) {
        item?.itemView?.startAnimation(animRemove)
    }

    override fun getRemoveDuration(): Long {
        return animRemove.duration
    }
}