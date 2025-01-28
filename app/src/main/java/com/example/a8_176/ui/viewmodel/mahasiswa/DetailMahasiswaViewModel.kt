package com.example.a8_176.ui.viewmodel.mahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class DetailMahasiswaViewModel(
    private val mhsRepository: MahasiswaRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailUiState())
        private set

    fun fetchDetailMahasiswa(id_mahasiswa: String) {
        viewModelScope.launch {
            uiState = DetailUiState(isLoading = true)
            try {
                val mahasiswa = mhsRepository.getMahasiswabyId(id_mahasiswa)
                uiState = DetailUiState(detailUiEvent = mahasiswa.toDetailUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

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