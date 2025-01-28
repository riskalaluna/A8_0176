package com.example.a8_176.ui.viewmodel.mahasiswa

import com.example.a8_176.model.Kamar
import com.example.a8_176.model.Mahasiswa

data class InsertMhsUiState(
    val insertMhsUiEvent: InsertMhsUiEvent = InsertMhsUiEvent(),
    val idkamarList: List<Kamar> = emptyList()
)
data class InsertMhsUiEvent(
    val id_mahasiswa: String = "",
    val nama_mahasiswa: String = "",
    val nomor_identitas: String = "",
    val email: String = "",
    val nomor_telepon: String = "",
    val id_kamar: String = "",
)

fun InsertMhsUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

fun Mahasiswa.toMhsUiState(): InsertMhsUiState = InsertMhsUiState(
    insertMhsUiEvent = toInsertMhsUiEvent()
)

fun Mahasiswa.toInsertMhsUiEvent(): InsertMhsUiEvent = InsertMhsUiEvent(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

