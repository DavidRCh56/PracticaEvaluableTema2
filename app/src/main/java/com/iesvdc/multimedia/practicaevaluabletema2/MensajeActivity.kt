package com.iesvdc.multimedia.practicaevaluabletema2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.enableEdgeToEdge

class MensajeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        enableEdgeToEdge()

        val editTextMessage = findViewById<EditText>(R.id.editTextMessage)
        val btnEnviarMensaje = findViewById<Button>(R.id.btnEnviarMensaje)
        val btnBackToMain = findViewById<Button>(R.id.btnBackToMain)

        btnEnviarMensaje.setOnClickListener {
            val message = editTextMessage.text.toString()
            if (message.isNotEmpty()) {
                sendMessage(message)
            } else {
                Toast.makeText(this, "Por favor, ingresa un mensaje para enviar", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackToMain.setOnClickListener {
            finish() // Regresa a MainActivity
        }
    }

    private fun sendMessage(message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar mensaje con"))
        } else {
            Toast.makeText(this, "No hay aplicaciones de mensajería disponibles", Toast.LENGTH_SHORT).show()
        }
    }
}
