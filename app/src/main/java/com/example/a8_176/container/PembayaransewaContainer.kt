package com.example.a8_176.container

import com.example.a8_176.repository.NetworkKontakPsRepository
import com.example.a8_176.repository.PembayaransewaRepository
import com.example.a8_176.service.PembayaransewaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppPsContainer {
    val kontakPsRepository: PembayaransewaRepository
}

class PembayaransewaContainer : AppPsContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/pembayaransewa/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pembayaransewaService: PembayaransewaService by lazy {
        retrofit.create(PembayaransewaService::class.java) }

    override val kontakPsRepository: PembayaransewaRepository by lazy {
        NetworkKontakPsRepository(pembayaransewaService)
    }
}