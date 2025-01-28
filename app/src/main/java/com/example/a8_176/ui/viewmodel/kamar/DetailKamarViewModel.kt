package com.example.a8_176.ui.viewmodel.kamar

import com.example.a8_176.model.Kamar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.repository.KamarRepository
import kotlinx.coroutines.launch

class DetailKamarViewModel(
    private val kmrRepository: KamarRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailKmrUiState())
        private set

    fun fetchDetailKamar(id_kamar: String) {
        viewModelScope.launch {
            uiState = DetailKmrUiState(isLoading = true)
            try {
                val kamar = kmrRepository.getKamarbyId(id_kamar)
                uiState = DetailKmrUiState(detailKmrUiEvent = kamar.toDetailKmrUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailKmrUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

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