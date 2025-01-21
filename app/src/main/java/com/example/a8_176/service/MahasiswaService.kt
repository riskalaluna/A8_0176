package com.example.a8_176.service

import com.example.a8_176.model.AllMahasiswaResponse
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
        "Content-Type: application/json",
    )

    //@GET("bacamahasiswa.php")
    @GET(".")
    suspend fun getAllMahasiswa(): AllMahasiswaResponse

    //@GET("baca1mahasiswa.php/{id_mahasiswa}")
    @GET("{id_mahasiswa}")
    suspend fun getMahasiswabyId(@Path("id_mahasiswa") id_mahasiswa:String): MahasiswaDetailResponse

    @POST("store")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    //@PUT("editmahasiswa.php/{id_mahasiswa}")
    @PUT("{id_mahasiswa}")
    suspend fun updateMahasiswa(@Path("id_mahasiswa") id_mahasiswa:String, @Body mahasiswa: Mahasiswa)

    //@DELETE("deletemahasiswa.php/{id_mahasiswa}")
    @DELETE("{id_mahasiswa}")
    suspend fun deleteMahasiswa (@Path("id_mahasiswa") id_mahasiswa: String): Response<Void>

}