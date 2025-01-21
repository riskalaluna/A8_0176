package com.example.a8_176

import android.app.Application
import com.example.a8_176.container.AppKmrContainer
import com.example.a8_176.container.KamarContainer

class KamarApplications:Application(){
    lateinit var container: AppKmrContainer
    override fun onCreate() {
        super.onCreate()
        container = KamarContainer()
    }
}