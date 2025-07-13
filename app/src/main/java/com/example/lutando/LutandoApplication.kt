package com.example.lutando

import android.app.Application
import com.example.lutando.di.initKoin

/**
 * Classe Application do Lutando.
 */
class LutandoApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
} 