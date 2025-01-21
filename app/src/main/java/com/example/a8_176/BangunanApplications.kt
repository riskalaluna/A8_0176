package com.example.a8_176

import android.app.Application
import com.example.a8_176.container.AppBgnContainer
import com.example.a8_176.container.BangunanContainer

class BangunanApplications:Application(){
    lateinit var container: AppBgnContainer
    override fun onCreate() {
        super.onCreate()
        container = BangunanContainer()
    }
}