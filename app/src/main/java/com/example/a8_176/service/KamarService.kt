package com.example.a8_176.service

import com.example.a8_176.model.AllKamarResponse
import com.example.a8_176.model.Kamar
import com.example.a8_176.model.KamarDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KamarService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    //@GET("bacakamar.php")
    @GET(".")
    suspend fun getAllKamar(): AllKamarResponse

    //@GET("baca1kamar.php/{id_kamar}")
    @GET("{id_kamar}")
    suspend fun getKamarbyId(@Path("iid_kamar") id_kamar: String): KamarDetailResponse

    @POST("store")
    suspend fun insertKamar(@Body kamar: Kamar)

    //@PUT("editkamar.php/{id_kamar}")
    @PUT("{id_kamar}")
    suspend fun updateKamar(@Path("id_kamar") id_kamar: String, @Body kamar: Kamar)

    //@DELETE("deletekamar.php/{id_kamar}")
    @DELETE("{id_kamar}")
    suspend fun deleteKamar (@Path("id_kamar") id_kamar: String): Response<Void>

}