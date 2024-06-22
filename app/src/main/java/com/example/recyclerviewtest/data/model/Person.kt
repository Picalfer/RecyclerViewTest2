package com.example.recyclerviewtest.data.model

data class Person(
    val id: Long,
    val name: String,
    val companyName: String,
    val photo: String, // ссылка на фото человека
    val isLiked: Boolean,
)
