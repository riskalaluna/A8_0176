package com.example.a8_176.service

import com.example.a8_176.model.AllBangunanResponse
import com.example.a8_176.model.Bangunan
import com.example.a8_176.model.BangunanDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BangunanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    //@GET("bacabangunan.php")
    @GET(".")
    suspend fun getAllBangunan(): AllBangunanResponse

    //@GET("baca1bangunan.php/{id_bangunan}")
    @GET("{id_bangunan}")
    suspend fun getBangunanbyId(@Path("id_bangunan") id_bangunan: String): BangunanDetailResponse

    @POST("store")
    suspend fun insertBangunan(@Body bangunan: Bangunan)

    //@PUT("editbangunan.php/{id_bangunan}")
    @PUT("{id_bangunan}")
    suspend fun updateBangunan(@Path("id_bangunan") id_bangunan: String, @Body bangunan: Bangunan)

    //@DELETE("deletebangunan.php/{id_bangunan}")
    @DELETE("{id_bangunan}")
    suspend fun deleteBangunan (@Path("id_bangunan") id_bangunan: String): Response<Void>
}