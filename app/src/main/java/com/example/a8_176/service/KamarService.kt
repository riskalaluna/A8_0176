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
        "Content-Type: application/json"
    )

    @GET("kamar")
    suspend fun getAllKamar(): AllKamarResponse

    @GET("kamar/{id_kamar}")
    suspend fun getKamarbyId(@Path("id_kamar") id_kamar: String): KamarDetailResponse

    @POST("kamar/store")
    suspend fun insertKamar(@Body kamar: Kamar)

    @PUT("kamar/{id_kamar}")
    suspend fun updateKamar(
        @Path("id_kamar") id_kamar: String,
        @Body kamar: Kamar
    ): Response<Kamar>

    @DELETE("kamar/{id_kamar}")
    suspend fun deleteKamar(@Path("id_kamar") id_kamar: String): Response<Void>
}
