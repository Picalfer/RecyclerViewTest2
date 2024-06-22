package com.example.recyclerviewtest.ui

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewtest.R
import com.example.recyclerviewtest.data.model.Person
import com.example.recyclerviewtest.databinding.ItemPersonBinding

interface PersonActionListener {
    fun onPersonGetId(person: Person)
    fun onPersonLike(person: Person)
    fun onPersonRemove(person: Person)
    fun onPersonMove(person: Person, moveBy: Int)
}

class PersonAdapter(private val personActionListener: PersonActionListener) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(), View.OnClickListener {
    var data: List<Person> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.likedImageView.setOnClickListener(this)
        binding.moreImageView.setOnClickListener(this)

        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data[position]
        val context = holder.itemView.context

        holder.itemView.tag = person
        holder.binding.likedImageView.tag = person
        holder.binding.moreImageView.tag = person

        with(holder.binding) {
            val color = if (person.isLiked) R.color.red else R.color.grey

            nameTextView.text = person.name
            companyTextView.text = person.companyName
            likedImageView.setColorFilter(
                ContextCompat.getColor(context, color),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            Glide.with(context)
                .load(person.photo)
                .circleCrop()
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground).into(imageView)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val person = view.tag as Person
        val position = data.indexOfFirst { it.id == person.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, "Up").apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, "Down").apply {
            isEnabled = position < data.size - 1
        }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, "Remove")

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> personActionListener.onPersonMove(person, -1)
                ID_MOVE_DOWN -> personActionListener.onPersonMove(person, 1)
                ID_REMOVE -> personActionListener.onPersonRemove(person)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    override fun onClick(view: View) {
        val person: Person = view.tag as Person // Получаем из тэга человека

        when (view.id) {
            R.id.moreImageView -> showPopupMenu(view)
            R.id.likedImageView -> personActionListener.onPersonLike(person)
            else -> personActionListener.onPersonGetId(person)
        }
    }

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
    }
}