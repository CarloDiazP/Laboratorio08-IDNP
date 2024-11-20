package com.example.laboratorio08.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.laboratorio08.data.local.entities.Environment
import com.example.laboratorio08.data.local.relations.EnvironmentWithPoints

@Dao
interface EnvironmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnvironment(environment: Environment): Long

    @Update
    suspend fun updateEnvironment(environment: Environment)

    @Delete
    suspend fun deleteEnvironment(environment: Environment)

    @Query("SELECT * FROM Environment")
    suspend fun getAllEnvironments(): List<Environment>

    @Transaction
    @Query("SELECT * FROM Environment WHERE id = :environmentId")
    suspend fun getEnvironmentWithPoints(environmentId: Long): EnvironmentWithPoints
}