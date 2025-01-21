package com.example.a8_176.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PembayaranSewa(
    val id_pembayaran: String,
    val id_mahasiswa: String,
    val jumlah: String,
    val tanggal_pembayaran: String,

    @SerialName("status_pembayaran")
    val status_pembayaran: String,
)