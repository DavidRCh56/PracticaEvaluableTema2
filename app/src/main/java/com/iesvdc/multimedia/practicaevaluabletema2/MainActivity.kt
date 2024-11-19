package com.iesvdc.multimedia.practicaevaluabletema2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val enlace = "https://pudding.cool/2021/04/music-bubble/"
        val btnLlamada = findViewById<Button>(R.id.btnLlamada)
        val btnUrl = findViewById<Button>(R.id.btnUrl)
        val btnAlarma = findViewById<Button>(R.id.btnAlarma)
        val btnPersonalizado = findViewById<Button>(R.id.btnPersonalizado)
        val btnJuegoDados = findViewById<Button>(R.id.btnJuegoDados)
        val btnChistes = findViewById<Button>(R.id.btnChistes)


        btnLlamada.setOnClickListener {
            startActivity(Intent(this, LlamadaActivity::class.java))
        }

        btnUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(enlace))
            startActivity(intent)
        }

        btnAlarma.setOnClickListener {
            startActivity(Intent(this, AlarmaActivity::class.java))
        }

        btnPersonalizado.setOnClickListener {
            val intent = Intent(this, MensajeActivity::class.java)
            startActivity(intent)
        }

        btnJuegoDados.setOnClickListener {
            val intent = Intent(this, JuegoDadosActivity::class.java)
            startActivity(intent)
        }

        btnChistes.setOnClickListener {
            val intent = Intent(this, JokerActivity::class.java)
            startActivity(intent)
        }
    }
}
