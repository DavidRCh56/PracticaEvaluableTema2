package com.iesvdc.multimedia.practicaevaluabletema2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.iesvdc.multimedia.practicaevaluabletema2.databinding.ActivityJuegoDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/*
Aplicación JuegoDados
Adaptada para utilizar activity_juego_dados.xml
 */
class JuegoDadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJuegoDadosBinding // Referencias a las vistas
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegoDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        binding.txtResultado.visibility = View.INVISIBLE
        binding.imageButton.setOnClickListener {
            binding.txtResultado.visibility = View.VISIBLE
            game() // Comienza el juego
        }
    }

    private fun game() {
        scheduleRun() // Planificamos las tiradas
    }

    private fun scheduleRun() {
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000
        for (i in 1..5) { // Lanzamos el dado 5 veces
            schedulerExecutor.schedule({
                throwDadoInTime() // Lanza los tres dados
            }, msc * i.toLong(), TimeUnit.MILLISECONDS)
        }

        schedulerExecutor.schedule({
            viewResult() // Mostramos el resultado final
        }, msc * 7.toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown() // Finalizamos el executor
    }

    private fun throwDadoInTime() {
        val numDados = Array(3) { Random.nextInt(1, 6) }
        val imagViews: Array<ImageView> = arrayOf(
            binding.imagviewDado1,
            binding.imagviewDado2,
            binding.imagviewDado3
        )

        sum = numDados.sum() // Calculamos la suma de los dados
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
        binding.txtResultado.text = sum.toString()
        println(sum)
    }
}
