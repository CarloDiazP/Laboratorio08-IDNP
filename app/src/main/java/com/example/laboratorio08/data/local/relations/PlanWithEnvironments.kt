package com.example.laboratorio08.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.laboratorio08.data.local.entities.Environment
import com.example.laboratorio08.data.local.entities.Plan

data class PlanWithEnvironments(
    @Embedded val plan: Plan,
    @Relation(
        parentColumn = "id",
        entityColumn = "planId"
    )
    val environments: List<Environment>
)