package com.appmovil.ciclodevidademo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log
class ActivityC : AppCompatActivity() {

    private val TAG = "CicloDeVida"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        Log.d(TAG, "ActivityC - onCreate - instancia: ${this.hashCode()}")

        var contadorActual = intent.getIntExtra("contador", 0)

        Log.d(TAG, "ActivityC recibió contador: $contadorActual")

        contadorActual++

        val resultado = Intent()
        resultado.putExtra("contador_actualizado", contadorActual)

        setResult(RESULT_OK, resultado)
        finish()

    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "ActivityC - onStart - instancia: ${this.hashCode()}")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "ActivityC - onResume - instancia: ${this.hashCode()}")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "ActivityC - onPause - instancia: ${this.hashCode()}")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "ActivityC - onStop - instancia: ${this.hashCode()}")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(TAG, "ActivityC - onRestart - instancia: ${this.hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "ActivityC - onDestroy - instancia: ${this.hashCode()}")
    }
}