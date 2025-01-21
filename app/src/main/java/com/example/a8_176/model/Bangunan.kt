package com.example.a8_176.model

import kotlinx.serialization.Serializable

@Serializable
data class Bangunan(
    val id_bangunan: String,
    val nama_bangunan: String,
    val jumlah_lantai: String,
    val alamat: String,
)