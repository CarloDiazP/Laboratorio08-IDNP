package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Edification(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val image: String,
    val planId: Long
)