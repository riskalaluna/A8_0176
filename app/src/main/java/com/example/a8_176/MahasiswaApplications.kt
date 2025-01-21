package com.example.a8_176

import android.app.Application
import com.example.a8_176.container.AppMhsContainer
import com.example.a8_176.container.MahasiswaContainer

class MahasiswaApplications:Application(){
    lateinit var container: AppMhsContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}