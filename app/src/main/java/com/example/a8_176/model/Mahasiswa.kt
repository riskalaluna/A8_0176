package com.example.a8_176.model

import kotlinx.serialization.Serializable

@Serializable
data class AllMahasiswaResponse (
    val status: Boolean,
    val message: String,
    val data: List<Mahasiswa>
)

@Serializable
data class Mahasiswa(
    val id_mahasiswa: String,
    val nama_mahasiswa: String,
    val nomor_identitas: String,
    val email: String,
    val nomor_telepon: String,
    val id_kamar: String
)