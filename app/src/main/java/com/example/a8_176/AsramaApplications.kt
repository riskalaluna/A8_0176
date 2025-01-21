package com.example.a8_176

import android.app.Application
import com.example.a8_176.container.AppContainer
import com.example.a8_176.container.AsramaContainer

class AsramaApplications:Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AsramaContainer()
    }
}