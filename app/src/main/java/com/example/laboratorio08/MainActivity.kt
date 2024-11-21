package com.example.laboratorio08

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.laboratorio08.data.database.AppDatabase
import com.example.laboratorio08.data.local.entities.Edification
import com.example.laboratorio08.data.local.entities.Environment
import com.example.laboratorio08.data.local.entities.Plan
import com.example.laboratorio08.data.local.entities.Point
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        lifecycleScope.launch {
            // Inserción de datos de ejemplo
            val planId = db.planDao().insertPlan(Plan())
            Log.d("planId", "$planId")
            val edification = Edification(
                name = "Catedral 1",
                description = "Catedral 1 descripcion",
                latitude = 12.3456,
                longitude = 65.4321,
                image = "catedral.png",
                planId = planId
            )

            val edification1 = db.edificationDao().insertEdification(edification)


            val environment1 = Environment(
                planId = planId,
                title = "Ambiente 1",
                description = "Descripcion ambiente 1",
                image = "ambiente1.png"
            )
            Log.d("environment1", "$environment1")
            val environment2 = Environment(
                planId = planId,
                title = "Ambiente 2",
                description = "Descripcion ambiente 2",
                image = "ambiente 2.png"
            )

            val environment1Id = db.environmentDao().insertEnvironment(environment1)
            val environment2Id = db.environmentDao().insertEnvironment(environment2)

            val point1 = Point(environmentId = environment1Id, x = 10, y = 20)
            val point2 = Point(environmentId = environment1Id, x = 30, y = 40)
            db.pointDao().insertPoint(point1)
            db.pointDao().insertPoint(point2)

            // Consulta Uno-a-Uno: Obtener Edification con su Plan
            val edificationWithPlan = db.edificationDao().getEdificationWithPlan(edification1)
            Log.d("MainActivity", "Edificación con su plan: $edificationWithPlan")

            // Consulta Uno-a-Muchos: Obtener Environment con sus Points
            val environmentWithPoints = db.environmentDao().getEnvironmentWithPoints(environment1Id)
            Log.d("MainActivity", "Environment con puntos: $environmentWithPoints")

            // Consulta Uno-a-Muchos: Obtener Plan con sus Environments
            val planWithEnvironments = db.planDao().getPlanWithEnvironments(planId)
            Log.d("MainActivity", "Plan con ambientes: $planWithEnvironments")
        }
    }

}