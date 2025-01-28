package com.example.a8_176.ui.viewmodel.mahasiswa

import com.example.a8_176.model.Mahasiswa

data class DetailUiState(
    val detailUiEvent: InsertMhsUiEvent = InsertMhsUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertMhsUiEvent()
}

fun Mahasiswa.toDetailUiEvent(): InsertMhsUiEvent {
    return InsertMhsUiEvent(
        id_mahasiswa = id_mahasiswa,
        nama_mahasiswa = nama_mahasiswa,
        nomor_identitas = nomor_identitas,
        email = email,
        nomor_telepon = nomor_telepon,
        id_kamar = id_kamar
    )
}