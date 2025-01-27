package com.example.a8_176.ui.viewmodel.kamar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Bangunan
import com.example.a8_176.model.Kamar
import com.example.a8_176.repository.KamarRepository
import kotlinx.coroutines.launch

class InsertKamarViewModel(
    private val kmr: KamarRepository
) : ViewModel() {
    var uiStateKamar by mutableStateOf(InsertKmrUiState())
        private set

    fun updateInsertKmrState(insertKmrUiEvent: InsertKmrUiEvent) {
        uiStateKamar = uiStateKamar.copy(insertKmrUiEvent = insertKmrUiEvent)
    }

    init {
        loadBangunan()
    }

    private fun loadBangunan() {
        viewModelScope.launch {
            try {
                val bangunanList = bgn.getBangunan()
                uiStateKamar = uiStateKamar.copy(idbangunanList = bangunanList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //Fungsi untuk menyimpan data kamar ke repository
    fun insertKmr() {
        viewModelScope.launch {
            try {
                kmr.insertKamar(uiStateKamar.insertKmrUiEvent.toKmr())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

//Data class untuk UI State
data class InsertKmrUiState(
    val insertKmrUiEvent: InsertKmrUiEvent = InsertKmrUiEvent(),
    val idbangunanList: List<Bangunan> = emptyList()
)

// Data class untuk event input
data class InsertKmrUiEvent(
    val id_kamar: String = "",
    val nomor_kamar: String = "",
    val id_bangunan: String = "",
    val kapasitas: String = "",
    val status_kamar: String = "",
)

// Fungsi konversi dari InsertKmrUiEvent ke Kamar
fun InsertKmrUiEvent.toKmr(): Kamar = Kamar(
    id_kamar = id_kamar,
    nomor_kamar = nomor_kamar,
    id_bangunan = id_bangunan,
    kapasitas = kapasitas,
    status_kamar = status_kamar
)

// Fungsi konversi dari Kamar ke InsertKmrUiState
fun Kamar.toKmrUiState(): InsertKmrUiState = InsertKmrUiState(
    insertKmrUiEvent = toInsertKmrUiEvent()
)

// Fungsi konversi dari Kamar ke InsertKmrUiEvent
fun Kamar.toInsertKmrUiEvent(): InsertKmrUiEvent = InsertKmrUiEvent(
    id_kamar = id_kamar,
    nomor_kamar = nomor_kamar,
    id_bangunan = id_bangunan,
    kapasitas = kapasitas,
    status_kamar = status_kamar
)
