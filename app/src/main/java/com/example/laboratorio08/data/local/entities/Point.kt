package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Point(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val environmentId: Long,
    val x: Int,
    val y: Int
)