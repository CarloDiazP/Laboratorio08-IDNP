package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Environment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val planId: Int,
    val title: String,
    val description: String,
    val image: String
)