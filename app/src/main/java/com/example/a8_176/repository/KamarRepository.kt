package com.example.a8_176.repository

import com.example.a8_176.model.Kamar
import com.example.a8_176.service.KamarService
import okio.IOException

interface KamarRepository {
    suspend fun getKamar(): List<Kamar>

    suspend fun insertKamar(kamar: Kamar)

    suspend fun updateKamar(id_kamar: String, kamar: Kamar)

    suspend fun deleteKamar(id_kamar: String)

    suspend fun getKamarbyId(id_kamar: String): Kamar
}

class NetworkKontakKmrRepository(
    private val kamarApiService: KamarService
): KamarRepository {
    override suspend fun getKamar(): List<Kamar> =
        kamarApiService.getAllKamar().data

    override suspend fun insertKamar(kamar: Kamar) {
        kamarApiService.insertKamar(kamar)
    }

    override suspend fun updateKamar(id_kamar: String, kamar: Kamar) {
        kamarApiService.updateKamar(id_kamar, kamar)
    }

    override suspend fun deleteKamar(id_kamar: String) {
        try{
            val response = kamarApiService.deleteKamar(id_kamar)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kamar. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }
    override suspend fun getKamarbyId(id_kamar: String): Kamar{
        return kamarApiService.getKamarbyId(id_kamar).data
    }
}