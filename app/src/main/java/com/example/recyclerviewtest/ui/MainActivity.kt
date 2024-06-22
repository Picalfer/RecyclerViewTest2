package com.example.recyclerviewtest.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtest.MainApplication
import com.example.recyclerviewtest.R
import com.example.recyclerviewtest.data.PersonService
import com.example.recyclerviewtest.databinding.ActivityMainBinding
import com.github.javafaker.App

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val personService: PersonService
        get() = (applicationContext as MainApplication).personService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val manager = LinearLayoutManager(this)
        adapter = PersonAdapter()
        adapter.data = personService.getPersons()

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }
}