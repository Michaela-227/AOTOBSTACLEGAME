package com.example.obstaclegame

import android.app.Application
import com.example.obstaclegame.utilities.SignalManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SignalManager.init(this)
    }
}