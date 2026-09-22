package com.appmovil.ciclodevidademo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityA : AppCompatActivity() {

    private val TAG = "CicloDeVida"
    private var contadorActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnAumentar = findViewById<Button>(R.id.btnAumentar)
        val btnDisminuir = findViewById<Button>(R.id.btnDisminuir)
        val txtContador = findViewById<TextView>(R.id.txtContador)

        contadorActual = savedInstanceState?.getInt("contador") ?: 0

        txtContador.text = "Contador: $contadorActual"

        btnAumentar.setOnClickListener {
            contadorActual++
            txtContador.text = "Contador: $contadorActual"
        }

        btnDisminuir.setOnClickListener {
            contadorActual--
            txtContador.text = "Contador: $contadorActual"
        }
        
        Log.d(TAG, "ActivityA - onCreate - instancia: ${this.hashCode()}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("contador", contadorActual)

        Log.d(TAG, "Estado guardado: $contadorActual")
    }

    override fun onStart() {
        super.onStart()

        Log.d(
            TAG,
            "ActivityA - onStart - instancia: ${this.hashCode()}"
        )
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            TAG,
            "ActivityA - onResume - instancia: ${this.hashCode()}"
        )
    }

    override fun onPause() {
        super.onPause()

        Log.d(
            TAG,
            "ActiuvityA - onPause - instancia: ${this.hashCode()}"
        )
    }

    override fun onStop() {
        super.onStop()

        Log.d(
            TAG,
            "ActivityA - onStop - instancia: ${this.hashCode()}"
        )
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(
            TAG,
            "ActivityA - onRestart - instancia: ${this.hashCode()}"
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(
            TAG,
            "ActivityA - onDestroy - instancia: ${this.hashCode()}"
        )
    }
}