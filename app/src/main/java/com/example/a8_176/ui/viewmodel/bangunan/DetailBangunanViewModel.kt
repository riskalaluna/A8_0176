package com.example.a8_176.ui.viewmodel.bangunan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Bangunan
import com.example.a8_176.repository.BangunanRepository
import kotlinx.coroutines.launch

class DetailBangunanViewModel(
    private val bgnRepository: BangunanRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailBgnUiState())
        private set

    fun fetchDetailBangunan(id_bangunan: String) {
        viewModelScope.launch {
            uiState = DetailBgnUiState(isLoading = true)  // Menyatakan bahwa data sedang dimuat
            try {
                //Mengambil data bangunan dari repository
                val bangunan = bgnRepository.getBangunanbyId(id_bangunan)

                // Mengubah state dengan data bangunan yang diterima
                uiState = DetailBgnUiState(detailBgnUiEvent = bangunan.toDetailBgnUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()

                // Menangani error jika request gagal
                uiState = DetailBgnUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

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