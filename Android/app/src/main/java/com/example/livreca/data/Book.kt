package com.example.livreca.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val name: String,
    val author: String,
    val genre: String,
    val progress: Int = 0,
    val numberOfPages: Int
)
