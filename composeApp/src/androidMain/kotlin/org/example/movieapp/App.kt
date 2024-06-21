package org.example.movieapp

import android.app.Application

class App : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        private var instance: App? = null

        fun requireInstance(): App {
            return instance!!
        }
    }
}