package com.example.a8_176.model

import kotlinx.serialization.Serializable

@Serializable
data class AllBangunanResponse (
    val status: Boolean,
    val message: String,
    val data: List<Bangunan>
)

@Serializable
data class BangunanDetailResponse (
    val status: Boolean,
    val message: String,
    val data: Bangunan
)

@Serializable
data class Bangunan(
    val id_bangunan: String,
    val nama_bangunan: String,
    val jumlah_lantai: String,
    val alamat: String,
)