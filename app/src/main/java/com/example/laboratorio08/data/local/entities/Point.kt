package com.example.laboratorio08.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Environment::class,
            parentColumns = ["id"],
            childColumns = ["environmentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Point(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val environmentId: Long, // Clave foránea que referencia a Environment.
    val x: Int,
    val y: Int
)