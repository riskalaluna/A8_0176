package com.example.a8_176.ui.viewmodel.bangunan

import com.example.a8_176.model.Bangunan

data class InsertBgnUiState(
    val insertBgnUiEvent: InsertBgnUiEvent = InsertBgnUiEvent()
)
data class InsertBgnUiEvent(
    val id_bangunan: String = "",
    val nama_bangunan: String = "",
    val jumlah_lantai: String = "",
    val alamat: String = "",
)

fun InsertBgnUiEvent.toBgn(): Bangunan = Bangunan(
    id_bangunan = id_bangunan,
    nama_bangunan = nama_bangunan,
    jumlah_lantai = jumlah_lantai,
    alamat = alamat
)

fun Bangunan.toBgnUiState(): InsertBgnUiState = InsertBgnUiState(
    insertBgnUiEvent = toInsertBgnUiEvent()
)
fun Bangunan.toInsertBgnUiEvent(): InsertBgnUiEvent = InsertBgnUiEvent(
    id_bangunan = id_bangunan,
    nama_bangunan = nama_bangunan,
    jumlah_lantai = jumlah_lantai,
    alamat = alamat
)