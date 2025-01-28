package com.example.a8_176.ui.viewmodel.pembayaransewa


import com.example.a8_176.model.Kamar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.PembayaranSewa
import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.repository.PembayaransewaRepository
import com.example.a8_176.ui.viewmodel.kamar.DetailKmrUiState
import com.example.a8_176.ui.viewmodel.kamar.toDetailKmrUiEvent
import kotlinx.coroutines.launch

class DetailPembayaranSewaViewModel(
    private val psRepository: PembayaransewaRepository
) : ViewModel() {
    var uiStatePs by mutableStateOf(DetailPsUiState())
        private set

    fun fetchDetailPembayaran(id_pembayaran: String) {
        viewModelScope.launch {
            uiStatePs = DetailPsUiState(isLoading = true)
            try {
                val pembayaranSewa = psRepository.getPembayaransewabyId(id_pembayaran)
                uiStatePs = DetailPsUiState(detailPsUiEvent = pembayaranSewa.toDetailPsUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiStatePs = DetailPsUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailPsUiState(
    val detailPsUiEvent: InsertPsUiEvent = InsertPsUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailPsUiEvent != InsertPsUiEvent()
}

fun PembayaranSewa.toDetailPsUiEvent(): InsertPsUiEvent {
    return InsertPsUiEvent(
        id_pembayaran = id_pembayaran,
        id_mahasiswa = id_mahasiswa,
        jumlah = jumlah,
        tanggal_pembayaran = tanggal_pembayaran,
        status_pembayaran = status_pembayaran,
    )
}