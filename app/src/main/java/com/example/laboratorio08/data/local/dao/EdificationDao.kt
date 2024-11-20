package com.example.laboratorio08.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.laboratorio08.data.local.entities.Edification
import com.example.laboratorio08.data.local.relations.EdificationWithPlan

@Dao
interface EdificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEdification(edification: Edification): Long

    @Update
    suspend fun updateEdification(edification: Edification)

    @Delete
    suspend fun deleteEdification(edification: Edification)

    @Transaction
    @Query("SELECT * FROM Edification")
    suspend fun getAllEdifications(): List<Edification>

    @Transaction
    @Query("SELECT * FROM Edification WHERE id = :edificationId")
    suspend fun getEdificationWithPlan(edificationId: Long): EdificationWithPlan
}