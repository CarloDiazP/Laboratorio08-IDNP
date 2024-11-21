package com.example.laboratorio08.data.local.dao



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.laboratorio08.data.local.entities.Plan
import com.example.laboratorio08.data.local.relations.PlanWithEnvironments


@Dao
interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: Plan): Long

    @Update
    suspend fun updatePlan(plan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)
/*
    @Query("SELECT * FROM planes")
    suspend fun getAllPlans(): List<Plan>
*/
    @Transaction
    @Query("SELECT * FROM planes WHERE id = :planId")
    suspend fun getPlanWithEnvironments(planId: Long): PlanWithEnvironments

    @Transaction
    @Query("SELECT * FROM planes")
    suspend fun getAllPlansWithEnvironments(): List<PlanWithEnvironments>
}