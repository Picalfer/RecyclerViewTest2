package com.landfathich.recyclerviewtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.recyclerviewtest.R

class ProductAdapter(var data: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder { // тут мы создаем View, она пока пустая, без данных
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) { // тут мы заполняем контентом созданный View данными из списка
        holder.icon.setImageResource(data[position].idIcon)
        holder.textName.text = data[position].name
        holder.textDesc.text = data[position].desc
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.find {
                it is String && it == "icon"
            }
                .let {
                    holder.icon.setImageResource(data[position].idIcon)
                }
            payloads.find {
                it is String && it == "name"
            }
                .let {
                    holder.textName.text = data[position].name
                }
            payloads.find {
                it is String && it == "desc"
            }
                .let {
                    holder.textDesc.text = data[position].desc
                }
        }

    }

    override fun getItemId(position: Int): Long {
        return data[position].hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { // создаем здесь переменные чтобы потом в других местах иметь доступ к ним через holder и изменять
        val icon = itemView.findViewById<ImageView>(R.id.icon)
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        val textDesc = itemView.findViewById<TextView>(R.id.text_desc)
    }

}