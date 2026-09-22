package com.appmovil.ciclodevidademo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
class ActivityB : AppCompatActivity() {

    private val TAG = "CicloDeVida"

    private val launcherC = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { resultado ->

        if (resultado.resultCode == RESULT_OK) {
            val nuevoContador =
                resultado.data?.getIntExtra("contador_actualizado", 0) ?: 0

            Log.d(TAG, "ActivityB recibió de ActivityC: $nuevoContador")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        Log.d(TAG, "ActivityB - onCreate - instancia: ${this.hashCode()}")

        val nombre = intent.getStringExtra("nombre_usuario")
        Log.d(TAG, "ActivityB recibió nombre: $nombre")

        //val intentC = Intent(this, ActivityC::class.java)
        //intentC.putExtra("contador", 10)

        //launcherC.launch(intentC)
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "ActivityB - onStart - instancia: ${this.hashCode()}")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "ActivityB - onResume - instancia: ${this.hashCode()}")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "ActivityB - onPause - instancia: ${this.hashCode()}")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "ActivityB - onStop - instancia: ${this.hashCode()}")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(TAG, "ActivityB - onRestart - instancia: ${this.hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "ActivityB - onDestroy - instancia: ${this.hashCode()}")
    }
}