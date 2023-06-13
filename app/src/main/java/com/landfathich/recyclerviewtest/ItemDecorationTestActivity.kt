package com.landfathich.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.recyclerviewtest.adapter.Product
import com.landfathich.recyclerviewtest.adapter.ProductAdapter
import com.skill_factory.unit3.animator.MyItemAnimator

class ItemDecorationTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_decoration_test)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = ProductAdapter(arrayListOf(
            Product(0, R.drawable.ic_apple, "Apple", "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."),
            Product(1, R.drawable.ic_banana, "Banana", "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."),
            Product(2, R.drawable.ic_lemon, "Lemon", "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."),
            Product(3, R.drawable.ic_pear, "Pear", "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown."),
            Product(4, R.drawable.ic_strawberry, "Strawberry", "A perennial herbaceous plant 5-20 cm high, with a thick brown rhizome. \"Mustache\" is short. The stem is thin."),
            Product(5, R.drawable.ic_orange, "Orange", "Orange juice is widely used as a drink in restaurants and cafes.")
        ))

        val newData = arrayListOf(
            Product(0, R.drawable.ic_apple, "Apple", "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."),
            Product(1, R.drawable.ic_banana, "Banana", "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."),
            Product(2, R.drawable.ic_lemon, "Lemon", "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."),
        )

        adapter.setHasStableIds(true) // подключаем StableIds
        recyclerView.adapter = adapter

        recyclerView.itemAnimator = MyItemAnimator(this) // так же можно getApplicationContext() или просто applicationContext

        adapter.data[0].idIcon = R.drawable.ic_banana
        adapter.notifyItemChanged(0, "icon")

        val diff = ProductDiff(adapter.data, newData)
        val diffResult = DiffUtil.calculateDiff(diff)

        adapter.data = newData
        diffResult.dispatchUpdatesTo(adapter)

        initializeButtons(adapter)
    }

    private fun initializeButtons(adapter: ProductAdapter) {
        val add = findViewById<Button>(R.id.add)
        val remove = findViewById<Button>(R.id.remove)

        add.setOnClickListener {
            adapter.data.add(
                Product(adapter.data.size, R.drawable.ic_orange, "Orange", "Orange juice is widely used as a drink in restaurants and cafes.")
            )
            adapter.notifyItemInserted(adapter.data.size-1)
        }
        remove.setOnClickListener {
            adapter.data.removeLast()
            adapter.notifyItemRemoved(adapter.data.size)
        }
    }
}