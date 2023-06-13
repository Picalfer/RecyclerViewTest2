package com.landfathich.recyclerviewtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.recyclerviewtest.adapter.MyAdapter
import com.landfathich.recyclerviewtest.decorator.MyItemDecoration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = MyAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(MyItemDecoration())

        val next_btn = findViewById<Button>(R.id.next_btn)
        next_btn.setOnClickListener {
            val intent = Intent(this, ItemDecorationTestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}