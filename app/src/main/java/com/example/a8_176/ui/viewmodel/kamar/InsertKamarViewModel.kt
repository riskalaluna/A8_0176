package com.example.a8_176.ui.viewmodel.kamar

import com.example.a8_176.model.Bangunan
import com.example.a8_176.model.Kamar

//Data class untuk UI State
data class InsertKmrUiState(
    val insertKmrUiEvent: InsertKmrUiEvent = InsertKmrUiEvent(),
    val idbangunanList: List<Bangunan> = emptyList()
)

// Data class untuk event input
data class InsertKmrUiEvent(
    val id_kamar: String = "",
    val nomor_kamar: String = "",
    val id_bangunan: String = "",
    val kapasitas: String = "",
    val status_kamar: String = "",
)

// Fungsi konversi dari InsertKmrUiEvent ke Kamar
fun InsertKmrUiEvent.toKmr(): Kamar = Kamar(
    id_kamar = id_kamar,
    nomor_kamar = nomor_kamar,
    id_bangunan = id_bangunan,
    kapasitas = kapasitas,
    status_kamar = status_kamar
)

// Fungsi konversi dari Kamar ke InsertKmrUiState
fun Kamar.toKmrUiState(): InsertKmrUiState = InsertKmrUiState(
    insertKmrUiEvent = toInsertKmrUiEvent()
)

// Fungsi konversi dari Kamar ke InsertKmrUiEvent
fun Kamar.toInsertKmrUiEvent(): InsertKmrUiEvent = InsertKmrUiEvent(
    id_kamar = id_kamar,
    nomor_kamar = nomor_kamar,
    id_bangunan = id_bangunan,
    kapasitas = kapasitas,
    status_kamar = status_kamar
)
