package com.example.a8_176


import android.app.Application
import com.example.a8_176.container.AppPsContainer
import com.example.a8_176.container.PembayaransewaContainer

class PembayaransewaApplications:Application(){
    lateinit var container: AppPsContainer
    override fun onCreate() {
        super.onCreate()
        container = PembayaransewaContainer()
    }
}