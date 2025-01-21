package com.example.a8_176.repository

import com.example.a8_176.model.Bangunan
import com.example.a8_176.service.BangunanService
import okio.IOException

interface BangunanRepository {
    suspend fun getBangunan(): List<Bangunan>

    suspend fun insertBangunan(bangunan: Bangunan)

    suspend fun updateBangunan(id_bangunan: String, bangunan: Bangunan)

    suspend fun deleteBangunan(id_bangunan: String)

    suspend fun getBangunanbyId(id_bangunan: String): Bangunan
}

class NetworkKontakBgnRepository(
    private val bangunanApiService: BangunanService
): BangunanRepository {
    override suspend fun getBangunan(): List<Bangunan> =
        bangunanApiService.getAllBangunan().data

    override suspend fun insertBangunan(bangunan: Bangunan) {
        bangunanApiService.insertBangunan(bangunan)
    }

    override suspend fun updateBangunan(id_bangunan: String, bangunan: Bangunan) {
        bangunanApiService.updateBangunan(id_bangunan, bangunan)
    }

    override suspend fun deleteBangunan(id_bangunan: String) {
        try{
            val response = bangunanApiService.deleteBangunan(id_bangunan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete bangunan. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }
    override suspend fun getBangunanbyId(id_bangunan: String): Bangunan{
        return bangunanApiService.getBangunanbyId(id_bangunan).data
    }
}