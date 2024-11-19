package com.example.laboratorio08.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.laboratorio08.data.local.entities.Edification

data class EdificationWithPlan(
    @Embedded val edification: Edification,
    @Relation(
        parentColumn = "planId",
        entityColumn = "id"
    )
    val plan: PlanWithEnvironments
)