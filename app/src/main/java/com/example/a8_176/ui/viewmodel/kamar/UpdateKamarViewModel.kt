package com.example.a8_176.ui.viewmodel.kamar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Bangunan
import com.example.a8_176.repository.BangunanRepository
import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.ui.view.kamar.DestinasiUpdateKmr
import kotlinx.coroutines.launch

class UpdateKamarViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryKamar: KamarRepository,
    private val repositoryBangunan: BangunanRepository
) : ViewModel() {

    // State untuk daftar bangunan
    var idbangunanList by mutableStateOf<List<Bangunan>>(emptyList())
        private set

    // State untuk data kamar
    var updateKmrUiState by mutableStateOf(InsertKmrUiState())
        private set

    // Mengambil id_kamar dari SavedStateHandle
    private val _id_kamar: String = checkNotNull(savedStateHandle[DestinasiUpdateKmr.id_kamar])

    init {
        viewModelScope.launch {
            try {
                val kamar = repositoryKamar.getKamarbyId(_id_kamar)
                updateKmrUiState = kamar.toKmrUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Memuat data bangunan
        viewModelScope.launch {
            try {
                val bangunanList = repositoryBangunan.getBangunan() // Harus List<Bangunan>
                idbangunanList = bangunanList
                updateKmrUiState = updateKmrUiState.copy(idbangunanList = bangunanList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Memperbarui state berdasarkan input user
    fun updateInsertKmrState(insertKmrUiEvent: InsertKmrUiEvent) {
        updateKmrUiState = updateKmrUiState.copy(insertKmrUiEvent = insertKmrUiEvent)
    }

    // Fungsi untuk update kamar
    fun updateKmr() {
        viewModelScope.launch {
            try {
                repositoryKamar.updateKamar(
                    _id_kamar,
                    updateKmrUiState.insertKmrUiEvent.toKmr()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
