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
        "Content-Type: application/json",
    )

    //@GET("bacapembayaransewa.php")
    @GET(".")
    suspend fun getAllPembayaransewa(): AllPembayaransewaResponse

    //@GET("baca1pembayaransewa.php/{id_pembayaran}")
    @GET("{id_pembayaran}")
    suspend fun getPembayaransewabyId(@Path("id_pembayaran") id_pembayaran: String): PembayaransewaDetailResponse

    @POST("store")
    suspend fun insertPembayaransewa(@Body pembayaranSewa: PembayaranSewa)

    //@PUT("editpembayaransewa.php/{id_pembayaran}")
    @PUT("{id_pembayaran}")
    suspend fun updatePembayaransewa(@Path("id_pembayaran") id_pembayaran: String, @Body pembayaranSewa: PembayaranSewa)

    //@DELETE("deletepembayaransewa.php/{id_pembayaran}")
    @DELETE("{id_pembayaran}")
    suspend fun deletePembayaransewa (@Path("id_pembayaran") id_pembayaran: String): Response<Void>

}