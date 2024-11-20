package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// Plan es palabra reservada, por ello se usa planes
@Entity(tableName = "planes")
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)