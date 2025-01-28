package com.example.a8_176.ui.viewmodel.mahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Bangunan
import com.example.a8_176.model.Kamar
import com.example.a8_176.repository.BangunanRepository
import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.repository.MahasiswaRepository
import com.example.a8_176.ui.view.kamar.DestinasiUpdateKmr
import com.example.a8_176.ui.view.mahasiswa.DestinasiUpdateMhs
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiEvent
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiState
import com.example.a8_176.ui.viewmodel.kamar.toKmr
import com.example.a8_176.ui.viewmodel.kamar.toKmrUiState
import kotlinx.coroutines.launch

class UpdateMahasiswaViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMahasiswa: MahasiswaRepository,
    private val repositoryKamar: KamarRepository
    //menambahkan repositoryKamar ke Mahasiswa viewmodel (salah commit file)
) : ViewModel() {
    var idkamarList by mutableStateOf<List<Kamar>>(emptyList())
        private set

    var updateMhsUiState by mutableStateOf(InsertMhsUiState())
        private set

    private val _id_mahasiswa: String = checkNotNull(savedStateHandle[DestinasiUpdateMhs.id_mahasiswa])

    init {
        viewModelScope.launch {
            try {
                val mahasiswa = repositoryMahasiswa.getMahasiswabyId(_id_mahasiswa)
                updateMhsUiState = mahasiswa.toMhsUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Memuat data bangunan
        viewModelScope.launch {
            try {
                val kamarList = repositoryKamar.getKamar()
                idkamarList = kamarList
                updateMhsUiState = updateMhsUiState.copy(idkamarList = kamarList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertMhsState(insertMhsUiEvent: InsertMhsUiEvent) {
        updateMhsUiState = updateMhsUiState.copy(insertMhsUiEvent = insertMhsUiEvent)
    }

    fun updateMhs() {
        viewModelScope.launch {
            try {
                repositoryMahasiswa.updateMahasiswa(
                    _id_mahasiswa,
                    updateMhsUiState.insertMhsUiEvent.toMhs()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
