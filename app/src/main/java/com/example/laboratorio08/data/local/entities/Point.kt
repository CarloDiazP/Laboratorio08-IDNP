package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Point(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val environmentId: Int,
    val x: Int,
    val y: Int
)