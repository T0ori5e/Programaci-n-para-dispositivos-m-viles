package com.appmovil.ciclodevidademo

import android.app.Application
import android.util.Log

class MiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d(
            "CicloDeVida",
            "MiApp.onCreate - el PROCESO arrancó"
        )
    }
}