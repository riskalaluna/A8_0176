package com.example.a8_176.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kamar(
    val id_kamar: String,
    val nomor_kamar: String,
    val id_bangunan: String,
    val kapasitas: String,

    @SerialName("status_kamar")
    val status_kamar: String,
)