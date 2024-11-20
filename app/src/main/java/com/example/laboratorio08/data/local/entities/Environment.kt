package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Plan::class,
            parentColumns = ["id"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Environment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val planId: Long, // Clave foránea que referencia a Plan.
    val title: String,
    val description: String,
    val image: String
)