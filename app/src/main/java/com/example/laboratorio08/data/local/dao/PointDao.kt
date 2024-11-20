package com.example.laboratorio08.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.laboratorio08.data.local.entities.Point

@Dao
interface PointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoint(point: Point): Long

    @Update
    suspend fun updatePoint(point: Point)

    @Delete
    suspend fun deletePoint(point: Point)

    @Query("SELECT * FROM Point WHERE environmentId = :environmentId")
    suspend fun getPointsByEnvironment(environmentId: Long): List<Point>
}
