package com.example.recyclerviewtest

import android.app.Application
import com.example.recyclerviewtest.data.PersonService

class MainApplication : Application() {
    val personService = PersonService()
}