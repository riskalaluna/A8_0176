package com.example.a8_176.ui.viewmodel.bangunan

import com.example.a8_176.model.Bangunan

data class DetailBgnUiState(
    val detailBgnUiEvent: InsertBgnUiEvent = InsertBgnUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailBgnUiEvent != InsertBgnUiEvent()
}

fun Bangunan.toDetailBgnUiEvent(): InsertBgnUiEvent {
    return InsertBgnUiEvent(
        id_bangunan = id_bangunan,
        nama_bangunan = nama_bangunan,
        jumlah_lantai = jumlah_lantai,
        alamat = alamat
    )
}