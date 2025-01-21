package com.example.a8_176.container

import com.example.a8_176.repository.BangunanRepository
import com.example.a8_176.repository.NetworkKontakBgnRepository
import com.example.a8_176.service.BangunanService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppBgnContainer {
    val kontakBgnRepository: BangunanRepository
}

class BangunanContainer : AppBgnContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/bangunan/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val bangunanService: BangunanService by lazy {
        retrofit.create(BangunanService::class.java) }

    override val kontakBgnRepository: BangunanRepository by lazy {
        NetworkKontakBgnRepository(bangunanService)
    }
}