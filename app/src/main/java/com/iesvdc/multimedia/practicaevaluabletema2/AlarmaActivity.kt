package com.iesvdc.multimedia.practicaevaluabletema2

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class AlarmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarma)
        enableEdgeToEdge()

        val messageInput = findViewById<EditText>(R.id.editTextMessage)
        val hourInput = findViewById<EditText>(R.id.editTextHour)
        val minuteInput = findViewById<EditText>(R.id.editTextMinute)
        val btnSetAlarm = findViewById<Button>(R.id.btnSetAlarm)
        val btnBackToMain = findViewById<Button>(R.id.btnBackToMain)

        btnSetAlarm.setOnClickListener {
            val message = messageInput.text.toString().ifEmpty { "Alarma" }
            val hour = hourInput.text.toString().toIntOrNull()
            val minute = minuteInput.text.toString().toIntOrNull()

            if (hour != null && minute != null && hour in 0..23 && minute in 0..59) {
                createAlarm(message, hour, minute)
            } else {
                Toast.makeText(this, "Por favor, ingresa una hora y minuto válidos", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackToMain.setOnClickListener {
            finish()
        }
    }

    private fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            Toast.makeText(this, "Alarma creada para las $hour:$minutes con mensaje: $message", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No hay aplicaciones de alarma disponibles", Toast.LENGTH_SHORT).show()
        }
    }
}
