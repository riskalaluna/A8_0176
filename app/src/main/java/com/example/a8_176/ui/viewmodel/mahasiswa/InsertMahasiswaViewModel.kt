package com.example.a8_176.ui.viewmodel.mahasiswa

import com.example.a8_176.model.Mahasiswa

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)
data class InsertUiEvent(
    val id_mahasiswa: String = "",
    val nama_mahasiswa: String = "",
    val nomor_identitas: String = "",
    val email: String = "",
    val nomor_telepon: String = "",
    val id_kamar: String = "",
)

fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)
fun Mahasiswa.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)