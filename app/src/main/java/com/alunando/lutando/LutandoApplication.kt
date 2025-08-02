package com.alunando.lutando

import android.app.Application
import com.alunando.lutando.di.initKoin
import com.google.firebase.FirebaseApp

/**
 * Classe Application do Lutando.
 */
class LutandoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this)
        FirebaseApp.initializeApp(this)
    }
} 