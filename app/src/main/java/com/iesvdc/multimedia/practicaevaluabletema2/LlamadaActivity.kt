package com.iesvdc.multimedia.practicaevaluabletema2

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class LlamadaActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextTelefono: EditText
    private lateinit var imgEditTelefono: ImageView

    // Variable para almacenar el número a llamar
    private var numeroTelefono: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamada)

        enableEdgeToEdge()

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("MiAppPrefs", MODE_PRIVATE)

        editTextTelefono = findViewById(R.id.editTextTelefono)
        val btnRealizarLlamada = findViewById<Button>(R.id.btnRealizarLlamada)
        imgEditTelefono = findViewById(R.id.imgEditTelefono)

        // Recuperar el número de teléfono almacenado y mostrarlo en el EditText
        val numeroGuardado = sharedPreferences.getString("numeroTelefono", "")
        editTextTelefono.setText(numeroGuardado)
        editTextTelefono.isEnabled = false // Desactivar el campo de edición por defecto

        // Acción para editar el número de teléfono
        imgEditTelefono.setOnClickListener {
            editTextTelefono.isEnabled = true // Permitir edición
            editTextTelefono.requestFocus()
        }

        // Acción para realizar la llamada
        btnRealizarLlamada.setOnClickListener {
            val numero = editTextTelefono.text.toString()

            if (numero.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa un número de teléfono", Toast.LENGTH_SHORT).show()
            } else {
                // Guardar el número de teléfono para futuras aperturas de la aplicación
                sharedPreferences.edit().putString("numeroTelefono", numero).apply()

                // Almacenar el número en la variable para usarlo si se necesita en el permiso
                numeroTelefono = numero

                // Realizar la llamada
                realizarLlamada(numero)
            }
        }
        val btnBackToMain = findViewById<Button>(R.id.btnVolverAlMain)
        btnBackToMain.setOnClickListener {
            finish() // Termina esta actividad y regresa a MainActivity
        }
    }

    private fun realizarLlamada(numero: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$numero"))
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Usar el número almacenado en numeroTelefono si se ha concedido el permiso
            numeroTelefono?.let { realizarLlamada(it) }
        } else {
            Toast.makeText(this, "Permiso de llamada no concedido", Toast.LENGTH_SHORT).show()
        }
    }
}
