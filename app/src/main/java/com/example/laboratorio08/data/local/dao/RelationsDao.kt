package com.example.laboratorio08.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.laboratorio08.data.local.relations.EdificationWithPlan
import com.example.laboratorio08.data.local.relations.EnvironmentWithPoints
import com.example.laboratorio08.data.local.relations.PlanWithEnvironments

@Dao
interface RelationsDao {
    @Transaction
    @Query("SELECT * FROM Edification")
    fun getEdificationsWithPlans(): List<EdificationWithPlan>

    @Transaction
    @Query("SELECT * FROM planes")
    fun getPlansWithEnvironments(): List<PlanWithEnvironments>

    @Transaction
    @Query("SELECT * FROM Environment")
    fun getEnvironmentsWithPoints(): List<EnvironmentWithPoints>
}