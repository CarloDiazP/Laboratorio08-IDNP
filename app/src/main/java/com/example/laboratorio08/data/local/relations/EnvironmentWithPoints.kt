package com.example.laboratorio08.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.laboratorio08.data.local.entities.Environment
import com.example.laboratorio08.data.local.entities.Point

data class EnvironmentWithPoints(
    @Embedded val environment: Environment,
    @Relation(
        parentColumn = "id",
        entityColumn = "environmentId"
    )
    val points: List<Point>
)