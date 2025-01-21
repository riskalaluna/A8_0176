package com.example.a8_176.container

import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.repository.NetworkKontakKmrRepository
import com.example.a8_176.service.KamarService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppKmrContainer {
    val kontakKmrRepository: KamarRepository
}

class KamarContainer : AppKmrContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/kamar/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val kamarService: KamarService by lazy {
        retrofit.create(KamarService::class.java) }

    override val kontakKmrRepository: KamarRepository by lazy {
        NetworkKontakKmrRepository(kamarService)
    }
}