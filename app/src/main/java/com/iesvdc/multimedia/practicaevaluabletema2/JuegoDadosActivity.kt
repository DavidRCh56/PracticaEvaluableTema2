package com.iesvdc.multimedia.practicaevaluabletema2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iesvdc.multimedia.practicaevaluabletema2.databinding.ActivityJuegoDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class JuegoDadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJuegoDadosBinding
    private var sum: Int = 0
    private var tiempoEntreTiradas: Long = 1 // Tiempo predeterminado en segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegoDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        setupEvents()
        val btnBackToMain = findViewById<Button>(R.id.btnVolver)
        btnBackToMain.setOnClickListener {
            finish() // Termina esta actividad y regresa a MainActivity
        }
    }

    private fun setupSpinner() {
        // Configuración del Spinner para seleccionar el tiempo entre tiradas
        binding.spinnerTiempo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tiempoEntreTiradas = (position + 1).toLong() // Valores: 1, 2 o 3 segundos
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Opcional, puedes dejar un valor predeterminado si no se selecciona nada
                tiempoEntreTiradas = 1 // Este es un valor predeterminado, puede ajustarse
            }
        }

        // Configurar el spinner para mostrar el valor predeterminado al iniciar
        binding.spinnerTiempo.setSelection(0)  // 0 es la primera opción de la lista
    }

    private fun setupEvents() {
        // Configuración de botones y otros elementos interactivos
        binding.btnJugar.setOnClickListener {
            if (binding.checkBoxSumar.isChecked) {
                game() // Inicia el juego
            } else {
                Toast.makeText(this, "Debes seleccionar al menos una opción para jugar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radio = findViewById<RadioButton>(checkedId)
            Toast.makeText(this, "Modo seleccionado: ${radio.text}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun game() {
        scheduleRun() // Planifica las tiradas
    }

    private fun scheduleRun() {
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        for (i in 1..5) {
            schedulerExecutor.schedule({
                throwDadoInTime()
            }, tiempoEntreTiradas * i, TimeUnit.SECONDS)
        }
        schedulerExecutor.schedule({
            viewResult()
        }, tiempoEntreTiradas * 6, TimeUnit.SECONDS)
        schedulerExecutor.shutdown()
    }

    private fun throwDadoInTime() {
        val numDados = Array(3) { Random.nextInt(1, 6) }
        val imagViews: Array<ImageView> = arrayOf(
            binding.imagviewDado1,
            binding.imagviewDado2,
            binding.imagviewDado3
        )

        sum = numDados.sum()
        for (i in numDados.indices) {
            selectView(imagViews[i], numDados[i])
        }
    }

    private fun selectView(imgV: ImageView, v: Int) {
        when (v) {
            1 -> imgV.setImageResource(R.drawable.dado1)
            2 -> imgV.setImageResource(R.drawable.dado2)
            3 -> imgV.setImageResource(R.drawable.dado3)
            4 -> imgV.setImageResource(R.drawable.dado4)
            5 -> imgV.setImageResource(R.drawable.dado5)
            6 -> imgV.setImageResource(R.drawable.dado6)
        }
    }

    private fun viewResult() {
        if (binding.radioSumar.isChecked) {
            binding.txtResultado.text = "Suma: $sum"
        } else {
            binding.txtResultado.text = "Última tirada: $sum"
        }
    }
}