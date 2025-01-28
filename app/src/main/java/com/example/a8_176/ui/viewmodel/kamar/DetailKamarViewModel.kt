package com.example.a8_176.ui.viewmodel.kamar

import com.example.a8_176.model.Kamar

data class DetailKmrUiState(
    val detailKmrUiEvent: InsertKmrUiEvent = InsertKmrUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailKmrUiEvent != InsertKmrUiEvent()
}

fun Kamar.toDetailKmrUiEvent(): InsertKmrUiEvent {
    return InsertKmrUiEvent(
        id_kamar = id_kamar,
        nomor_kamar = nomor_kamar,
        id_bangunan = id_bangunan,
        kapasitas = kapasitas,
        status_kamar = status_kamar,
    )
}