package com.landfathich.recyclerviewtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.landfathich.recyclerviewtest.R
import com.landfathich.recyclerviewtest.model.Item
import com.landfathich.recyclerviewtest.model.Product

class ProductAdapter() :
    ListDelegationAdapter<List<Item>>() {

    init {
            delegatesManager.addDelegate(AdDelegateAdapter())
            delegatesManager.addDelegate(ProductDelegateAdapter())
    }

    override fun setItems(items: List<Item>?) {
        super.setItems(items)
        notifyDataSetChanged() // можно реализовать diffutils управление
    }
}