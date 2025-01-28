package com.example.a8_176.ui.viewmodel.pembayaransewa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Bangunan
import com.example.a8_176.model.Mahasiswa

import com.example.a8_176.repository.MahasiswaRepository
import com.example.a8_176.repository.PembayaransewaRepository
import com.example.a8_176.ui.view.pembayaransewa.DestinasiUpdatePs
import kotlinx.coroutines.launch

class UpdatePembayaranSewaViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryPembayaran: PembayaransewaRepository,
    private val repositoryMahasiswa: MahasiswaRepository
) : ViewModel() {
    var idmahasiswaList by mutableStateOf<List<Mahasiswa>>(emptyList())
        private set

    var updatePsUiState by mutableStateOf(InsertPsUiState())
        private set

    //private val _id_pembayaran: String = checkNotNull(savedStateHandle[DestinasiUpdatePs.id_pembayaran])
    private val _id_pembayaran: String = savedStateHandle[DestinasiUpdatePs.id_pembayaran]
        ?: throw IllegalArgumentException("id_pembayaran is required but was null")

    init {
        viewModelScope.launch {
            try {
                val pembayaranSewa = repositoryPembayaran.getPembayaransewabyId(_id_pembayaran)
                updatePsUiState = pembayaranSewa.toPsUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                val mahasiswaList = repositoryMahasiswa.getMahasiswa()
                idmahasiswaList = mahasiswaList
                updatePsUiState = updatePsUiState.copy(idmahasiswaList = mahasiswaList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Memperbarui state berdasarkan input user
    fun updateInsertPsState(insertPsUiEvent: InsertPsUiEvent) {
        updatePsUiState = updatePsUiState.copy(insertPsUiEvent = insertPsUiEvent)
    }

    // Fungsi untuk update pembayaran
    fun updatePs() {
        viewModelScope.launch {
            try {
                repositoryPembayaran.updatePembayaransewa(
                    _id_pembayaran,
                    updatePsUiState.insertPsUiEvent.toPs()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
