package com.example.a8_176.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllKamarResponse (
    val status: Boolean,
    val message: String,
    val data: List<Kamar>
)

@Serializable
data class KamarDetailResponse (
    val status: Boolean,
    val message: String,
    val data: Kamar
)

@Serializable
data class Kamar(
    val id_kamar: String,
    val nomor_kamar: String,
    val id_bangunan: String,
    val kapasitas: String,

    @SerialName("status_kamar")
    val status_kamar: String,
)