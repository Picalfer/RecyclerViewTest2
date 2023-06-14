package com.landfathich.recyclerviewtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.landfathich.recyclerviewtest.R
import com.landfathich.recyclerviewtest.model.Item
import com.landfathich.recyclerviewtest.model.Product

class ProductDelegateAdapter :
    AbsListItemAdapterDelegate<Product, Item, ProductDelegateAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { // создаем здесь переменные чтобы потом в других местах иметь доступ к ним через holder и изменять
        val icon = itemView.findViewById<ImageView>(R.id.icon)
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        val textDesc = itemView.findViewById<TextView>(R.id.text_desc)
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Product
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(item: Product, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.icon.setImageResource(item.idIcon)
        holder.textName.text = item.name
        holder.textDesc.text = item.desc


        payloads.find {
            it is String && it == "icon"
        }
            .let {
                holder.icon.setImageResource(item.idIcon)
            }
        payloads.find {
            it is String && it == "name"
        }
            .let {
                holder.textName.text = item.name
            }
        payloads.find {
            it is String && it == "desc"
        }
            .let {
                holder.textDesc.text = item.desc
            }
    }
}