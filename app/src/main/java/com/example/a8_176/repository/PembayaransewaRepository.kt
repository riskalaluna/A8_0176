package com.example.a8_176.repository

import com.example.a8_176.model.PembayaranSewa
import com.example.a8_176.service.PembayaransewaService
import okio.IOException

interface PembayaransewaRepository {
    suspend fun getPembayaransewa(): List<PembayaranSewa>

    suspend fun insertPembayaransewa(pembayaranSewa: PembayaranSewa)

    suspend fun updatePembayaransewa(id_pembayaran: String, pembayaranSewa: PembayaranSewa)

    suspend fun deletePembayaransewa(id_pembayaran: String)

    suspend fun getPembayaransewabyId(id_pembayaran: String): PembayaranSewa
}

class NetworkKontakPsRepository(
    private val pembayaransewaApiService: PembayaransewaService
): PembayaransewaRepository {
    override suspend fun getPembayaransewa(): List<PembayaranSewa> =
        pembayaransewaApiService.getAllPembayaransewa().data

    override suspend fun insertPembayaransewa(pembayaranSewa: PembayaranSewa) {
        pembayaransewaApiService.insertPembayaransewa(pembayaranSewa)
    }

    override suspend fun updatePembayaransewa(id_pembayaran: String, pembayaranSewa: PembayaranSewa) {
        pembayaransewaApiService.updatePembayaransewa(id_pembayaran, pembayaranSewa)
    }

    override suspend fun deletePembayaransewa(id_pembayaran: String) {
        try{
            val response = pembayaransewaApiService.deletePembayaransewa(id_pembayaran)
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
    override suspend fun getPembayaransewabyId(id_pembayaran: String): PembayaranSewa{
        return pembayaransewaApiService.getPembayaransewabyId(id_pembayaran).data
    }
}