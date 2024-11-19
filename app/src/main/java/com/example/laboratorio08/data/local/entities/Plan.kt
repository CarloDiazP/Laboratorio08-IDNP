package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)