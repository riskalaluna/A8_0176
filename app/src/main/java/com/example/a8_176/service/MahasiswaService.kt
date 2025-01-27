package com.example.a8_176.service

import com.example.a8_176.model.AllMahasiswaResponse
import com.example.a8_176.model.Kamar
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.model.MahasiswaDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("mahasiswa")
    suspend fun getAllMahasiswa(): AllMahasiswaResponse

    @GET("mahasiswa/{id_mahasiswa}")
    suspend fun getMahasiswabyId(@Path("id_mahasiswa") id_mahasiswa: String): MahasiswaDetailResponse

    @POST("mahasiswa/store")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    @PUT("mahasiswa/{id_mahasiswa}")
    suspend fun updateMahasiswa(
        @Path("id_mahasiswa") id_mahasiswa: String,
        @Body mahasiswa: Mahasiswa
    ): Response<Mahasiswa>

    @DELETE("mahasiswa/{id_mahasiswa}")
    suspend fun deleteMahasiswa(@Path("id_mahasiswa") id_mahasiswa: String): Response<Void>
}
