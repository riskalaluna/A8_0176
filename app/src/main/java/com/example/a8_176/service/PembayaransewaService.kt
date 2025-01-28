package com.example.a8_176.service

import com.example.a8_176.model.AllPembayaransewaResponse
import com.example.a8_176.model.PembayaranSewa
import com.example.a8_176.model.PembayaransewaDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PembayaransewaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("pembayaransewa")
    suspend fun getAllPembayaransewa(): AllPembayaransewaResponse

    @GET("pembayaransewa/{id_pembayaran}")
    suspend fun getPembayaransewabyId(@Path("id_pembayaran") id_pembayaran: String): PembayaransewaDetailResponse

    @POST("pembayaransewa/store")
    suspend fun insertPembayaransewa(@Body pembayaranSewa: PembayaranSewa)

    @PUT("pembayaransewa/{id_pembayaran}")
    suspend fun updatePembayaransewa(
        @Path("id_pembayaran") id_pembayaran: String,
        @Body pembayaranSewa: PembayaranSewa
    ): Response<PembayaranSewa>

    @DELETE("pembayaransewa/{id_pembayaran}")
    suspend fun deletePembayaransewa(@Path("id_pembayaran") id_pembayaran: String): Response<Void>
}
