package com.example.a8_176.container

import com.example.a8_176.repository.MahasiswaRepository
import com.example.a8_176.repository.NetworkKontakMhsRepository
import com.example.a8_176.service.MahasiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppMhsContainer {
    val kontakMhsRepository: MahasiswaRepository
}

class MahasiswaContainer : AppMhsContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/mahasiswa/" //localhost diganti ip kl run di hp
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val mahasiswaService: MahasiswaService by lazy {
        retrofit.create(MahasiswaService::class.java) }

    override val kontakMhsRepository: MahasiswaRepository by lazy {
        NetworkKontakMhsRepository(mahasiswaService)
    }
}