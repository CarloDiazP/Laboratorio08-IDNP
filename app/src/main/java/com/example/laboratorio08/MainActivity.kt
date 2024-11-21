package com.example.laboratorio08

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.laboratorio08.data.database.AppDatabase
import com.example.laboratorio08.databinding.ActivityMainBinding
import com.example.laboratorio08.adapter.DataAdapter
import com.example.laboratorio08.data.local.entities.Edification
import com.example.laboratorio08.data.local.entities.Environment
import com.example.laboratorio08.data.local.entities.Plan
import com.example.laboratorio08.data.local.entities.Point
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var binding: ActivityMainBinding
    private val dataList = mutableListOf<String>()
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar la base de datos
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        // Configurar RecyclerView
        adapter = DataAdapter(dataList)
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = adapter

        // Configurar el botón para cargar datos
        binding.btnLoadData.setOnClickListener {
            lifecycleScope.launch {
                loadAndDisplayData()
                Log.d("test", "pressed")
            }
        }
    }

    private suspend fun loadAndDisplayData() {
        // Inserción de datos de ejemplo
        val planId = db.planDao().insertPlan(Plan())
        val currentMillis = System.currentTimeMillis()
        val edification = Edification(
            name = "Catedral ${currentMillis}",
            description = "Catedral ${currentMillis} descripción",
            latitude = 12.3456,
            longitude = 65.4321,
            image = "catedral.png",
            planId = planId
        )
        db.edificationDao().insertEdification(edification)

        val environment1 = Environment(
            planId = planId,
            title = "Ambiente 1",
            description = "Descripción ambiente 1",
            image = "ambiente1.png"
        )
        val environment2 = Environment(
            planId = planId,
            title = "Ambiente 2",
            description = "Descripción ambiente 2",
            image = "ambiente2.png"
        )
        val environment1Id = db.environmentDao().insertEnvironment(environment1)
        val environment2Id = db.environmentDao().insertEnvironment(environment2)

        db.pointDao().insertPoint(Point(environmentId = environment1Id, x = Random.nextInt(1, 101), y = Random.nextInt(1, 101)))
        db.pointDao().insertPoint(Point(environmentId = environment2Id, x = Random.nextInt(1, 101), y = Random.nextInt(1, 101)))

        // Consultar relaciones
        val edificationsWithPlans = db.edificationDao().getAllEdificationsWithPlans()
        val plansWithEnvironments = db.planDao().getAllPlansWithEnvironments()
        val environmentsWithPoints = db.environmentDao().getAllEnvironmentsWithPoints()

        // Construir datos jerárquicos
        dataList.clear()
        for (edificationWithPlan in edificationsWithPlans) {
            dataList.add("Edificación: ${edificationWithPlan.edification.name}")
            dataList.add("\tPlano: ${edificationWithPlan.plan?.id}")

            val planEnvironments = plansWithEnvironments.find { it.plan.id == edificationWithPlan.plan?.id }?.environments.orEmpty()
            for (environment in planEnvironments) {
                dataList.add("\t\tAmbiente: ${environment.title}")
                val points = environmentsWithPoints.find { it.environment.id == environment.id }?.points.orEmpty()
                for (point in points) {
                    dataList.add("\t\t\tPunto: (${point.x}, ${point.y})")
                }
            }
        }

        // Notificar cambios al adaptador
        adapter.notifyDataSetChanged()
    }

}
