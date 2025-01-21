package com.example.a8_176.repository

import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.service.MahasiswaService
import okio.IOException

interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(id_mahasiswa: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(id_mahasiswa: String)

    suspend fun getMahasiswabyId(id_mahasiswa: String): Mahasiswa
}

class NetworkKontakMhsRepository(
    private val mahasiswaApiService: MahasiswaService
): MahasiswaRepository {
    override suspend fun getMahasiswa(): List<Mahasiswa> =
        mahasiswaApiService.getAllMahasiswa().data

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(id_mahasiswa: String, mahasiswa: Mahasiswa) {
        mahasiswaApiService.updateMahasiswa(id_mahasiswa, mahasiswa)
    }

    override suspend fun deleteMahasiswa(id_mahasiswa: String) {
        try{
            val response = mahasiswaApiService.deleteMahasiswa(id_mahasiswa)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete mahasiswa. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }
    override suspend fun getMahasiswabyId(id_mahasiswa: String): Mahasiswa {
        return mahasiswaApiService.getMahasiswabyId(id_mahasiswa).data
    }
}