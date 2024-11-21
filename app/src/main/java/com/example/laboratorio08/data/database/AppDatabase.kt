package com.example.laboratorio08.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.laboratorio08.data.local.dao.EdificationDao
import com.example.laboratorio08.data.local.dao.EnvironmentDao
import com.example.laboratorio08.data.local.dao.PlanDao
import com.example.laboratorio08.data.local.dao.PointDao
import com.example.laboratorio08.data.local.entities.Edification
import com.example.laboratorio08.data.local.entities.Environment
import com.example.laboratorio08.data.local.entities.Plan
import com.example.laboratorio08.data.local.entities.Point

@Database(entities = [Edification::class, Environment::class, Plan::class, Point::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun edificationDao(): EdificationDao
    abstract fun planDao(): PlanDao
    abstract fun environmentDao(): EnvironmentDao
    abstract fun pointDao(): PointDao
}