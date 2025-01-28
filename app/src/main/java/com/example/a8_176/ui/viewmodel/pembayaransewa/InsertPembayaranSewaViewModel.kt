package com.example.a8_176.ui.viewmodel.pembayaransewa

import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.model.PembayaranSewa

data class InsertPsUiState(
    val insertPsUiEvent: InsertPsUiEvent = InsertPsUiEvent(),
    val idmahasiswaList: List<Mahasiswa> = emptyList()
)
data class InsertPsUiEvent(
    val id_pembayaran: String = "",
    val id_mahasiswa: String = "",
    val jumlah: String = "",
    val tanggal_pembayaran: String = "",
    val status_pembayaran:  String = "",
)

fun InsertPsUiEvent.toPs(): PembayaranSewa = PembayaranSewa(
    id_pembayaran = id_pembayaran,
    id_mahasiswa = id_mahasiswa,
    jumlah = jumlah,
    tanggal_pembayaran = tanggal_pembayaran,
    status_pembayaran = status_pembayaran
)

fun PembayaranSewa.toPsUiState(): InsertPsUiState = InsertPsUiState(
    insertPsUiEvent = toInsertPsUiEvent()
)
fun PembayaranSewa.toInsertPsUiEvent(): InsertPsUiEvent = InsertPsUiEvent(
    id_pembayaran = id_pembayaran,
    id_mahasiswa = id_mahasiswa,
    jumlah = jumlah,
    tanggal_pembayaran = tanggal_pembayaran,
    status_pembayaran = status_pembayaran
)