package com.example.recyclerviewtest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtest.MainApplication
import com.example.recyclerviewtest.R
import com.example.recyclerviewtest.data.PersonListener
import com.example.recyclerviewtest.data.PersonService
import com.example.recyclerviewtest.data.model.Person
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val personService: PersonService
        get() = (applicationContext as MainApplication).personService
    private val listener: PersonListener = {adapter.data = it}

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
        adapter = PersonAdapter(object : PersonActionListener { // Создание объекта
            override fun onPersonGetId(person: Person) =
                Toast.makeText(this@MainActivity, "Persons ID: ${person.id}", Toast.LENGTH_SHORT).show()

            override fun onPersonLike(person: Person) = personService.likePerson(person)

            override fun onPersonRemove(person: Person) = personService.removePerson(person)

            override fun onPersonMove(person: Person, moveBy: Int) = personService.movePerson(person, moveBy)

        })

        adapter.data = personService.getPersons()

        personService.addListener(listener)

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }
}