package com.example.a8_176.container

import com.example.a8_176.repository.BangunanRepository
import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.repository.MahasiswaRepository
import com.example.a8_176.repository.NetworkKontakBgnRepository
import com.example.a8_176.repository.NetworkKontakKmrRepository
import com.example.a8_176.repository.NetworkKontakMhsRepository
import com.example.a8_176.repository.NetworkKontakPsRepository
import com.example.a8_176.repository.PembayaransewaRepository
import com.example.a8_176.service.BangunanService
import com.example.a8_176.service.KamarService
import com.example.a8_176.service.MahasiswaService
import com.example.a8_176.service.PembayaransewaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val kontakPsRepository: PembayaransewaRepository
    val kontakMhsRepository: MahasiswaRepository
    val kontakKmrRepository: KamarRepository
    val kontakBgnRepository: BangunanRepository
}

class AsramaContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/Routes/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    // Service untuk Pembayaran Sewa
    private val pembayaransewaService: PembayaransewaService by lazy {
        retrofit.create(PembayaransewaService::class.java)
    }

    // Service untuk Mahasiswa
    private val mahasiswaService: MahasiswaService by lazy {
        retrofit.create(MahasiswaService::class.java)
    }

    // Service untuk Kamar
    private val kamarService: KamarService by lazy {
        retrofit.create(KamarService::class.java)
    }

    // Service untuk Bangunan
    private val bangunanService: BangunanService by lazy {
        retrofit.create(BangunanService::class.java)
    }


    // Repository untuk Pembayaran Sewa
    override val kontakPsRepository: PembayaransewaRepository by lazy {
        NetworkKontakPsRepository(pembayaransewaService)
    }

    // Repository untuk Mahasiswa
    override val kontakMhsRepository: MahasiswaRepository by lazy {
        NetworkKontakMhsRepository(mahasiswaService)
    }

    // Repository untuk Kamar
    override val kontakKmrRepository: KamarRepository by lazy {
        NetworkKontakKmrRepository(kamarService)
    }

    // Repository untuk Bangunan
    override val kontakBgnRepository: BangunanRepository by lazy {
        NetworkKontakBgnRepository(bangunanService)
    }
}
