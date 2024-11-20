package com.example.laboratorio08.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.laboratorio08.data.local.entities.Edification
import com.example.laboratorio08.data.local.entities.Plan

data class EdificationWithPlan(
    @Embedded val edification: Edification,
    @Relation(
        parentColumn = "planId", // planId en Edification
        entityColumn = "id" // id en Plan
    )
    val plan: Plan
)