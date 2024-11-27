package com.iesvdc.multimedia.practicaevaluabletema2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import com.iesvdc.multimedia.practicaevaluabletema2.databinding.ActivityJokerBinding
import java.util.*

class JokerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokerBinding
    private lateinit var textToSpeech: TextToSpeech  // Descriptor de voz
    private val TOUCH_MAX_TIME = 500 // En milisegundos
    private var touchLastTime: Long = 0  // Para saber el tiempo entre toques.
    private lateinit var handler: Handler
    val MYTAG = "LOGCAT"  // Para mirar logs

    private val chistes = listOf(
        R.string.chiste_1, R.string.chiste_2, R.string.chiste_3, R.string.chiste_4,
        R.string.chiste_5, R.string.chiste_6, R.string.chiste_7, R.string.chiste_8,
        R.string.chiste_9, R.string.chiste_10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTextToSpeech()  // Configuramos nuestro textToSpeech
        initHander()    // Lanzamos un hilo para el progressBar
        initEvent()     // Implementación del botón
        val btnBackToMain = findViewById<Button>(R.id.btnVolver)
        btnBackToMain.setOnClickListener {
            finish() // Termina esta actividad y regresa a MainActivity
        }
    }

    private fun initHander() {
        handler = Handler(Looper.getMainLooper())  // Queremos que el tema de la IU, la llevemos al hilo principal.
        binding.progressBar.visibility = View.VISIBLE  // Hacemos visible el progress
        binding.btnAccion.visibility = View.GONE  // Ocultamos el botón de acción.
        binding.btnVolver.visibility = View.GONE  // Ocultamos el botón de volver.

        Thread {
            Thread.sleep(3000)
            handler.post {
                binding.progressBar.visibility = View.GONE  // Ocultamos el progress
                val description = getString(R.string.describe).toString()
                speakMeDescription(description)  // Que nos comente de qué va esto...
                Log.i(MYTAG, "Se ejecuta correctamente el hilo")
                binding.btnAccion.visibility = View.VISIBLE
                binding.btnVolver.visibility = View.VISIBLE  // Hacemos visible el botón de volver después de la carga.
            }
        }.start()
    }

    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if (it != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.getDefault()
                Log.i(MYTAG, "Sin problemas en la configuración TextToSpeech")
            } else {
                Log.i(MYTAG, "Error en la configuración TextToSpeech")
            }
        })
    }

    private fun initEvent() {
        binding.btnAccion.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - touchLastTime < TOUCH_MAX_TIME) {
                val chiste = getRandomChiste()
                executorDoubleTouch(chiste)  // Hemos pulsado dos veces, lanzamos el chiste.
                Log.i(MYTAG, "Escuchamos el chiste")
                showProgressBarWhileSpeaking()  // Muestra el ProgressBar mientras se habla el chiste.
            } else {
                Log.i(MYTAG, "Hemos pulsado 1 vez.")
                speakMeDescription("Botón para escuchar un chiste")
            }
            touchLastTime = currentTime
        }
    }

    // Habla
    private fun speakMeDescription(s: String) {
        Log.i(MYTAG, "Intenta hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun executorDoubleTouch(chisteResId: Int) {
        val chiste = getString(chisteResId)
        speakMeDescription(chiste)
    }

    private fun getRandomChiste(): Int {
        return chistes.random()  // Obtiene un chiste aleatorio de la lista
    }

    // Muestra el ProgressBar mientras se está hablando el chiste y lo oculta al finalizar
    private fun showProgressBarWhileSpeaking() {
        binding.progressBar.visibility = View.VISIBLE  // Muestra el ProgressBar
        binding.btnAccion.visibility = View.GONE  // Oculta el botón de acción mientras se escucha el chiste
        binding.btnVolver.visibility = View.GONE  // Oculta el botón de volver mientras se escucha el chiste

        // Necesitamos un delay para ocultar el ProgressBar después de que termine de hablar el chiste
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBar.visibility = View.GONE  // Oculta el ProgressBar
            binding.btnAccion.visibility = View.VISIBLE  // Muestra el botón de acción de nuevo
            binding.btnVolver.visibility = View.VISIBLE  // Muestra el botón de volver nuevamente
        }, 10000)  // La duración del ProgressBar es ahora de 10 segundos (10000 milisegundos)
    }

    override fun onDestroy() {
        // Si hemos inicializado la propiedad textToSpeech, es porque existe.
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
