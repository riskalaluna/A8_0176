package com.example.a8_176.ui.viewmodel.bangunan

import com.example.a8_176.model.Bangunan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.repository.BangunanRepository
import kotlinx.coroutines.launch

class InsertBangunanViewModel(
    private val bgn: BangunanRepository
): ViewModel() {
    var uiState by mutableStateOf(InsertBgnUiState())
        private set

    fun updateInsertBgnState(insertBgnUiEvent: InsertBgnUiEvent){
        uiState = InsertBgnUiState(insertBgnUiEvent = insertBgnUiEvent)
    }

    fun insertBgn() {
        viewModelScope.launch {
            try {
                bgn.insertBangunan(uiState.insertBgnUiEvent.toBgn())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertBgnUiState(
    val insertBgnUiEvent: InsertBgnUiEvent = InsertBgnUiEvent()
)
data class InsertBgnUiEvent(
    val id_bangunan: String = "",
    val nama_bangunan: String = "",
    val jumlah_lantai: String = "",
    val alamat: String = "",
)

fun InsertBgnUiEvent.toBgn(): Bangunan = Bangunan(
    id_bangunan = id_bangunan,
    nama_bangunan = nama_bangunan,
    jumlah_lantai = jumlah_lantai,
    alamat = alamat
)

fun Bangunan.toBgnUiState(): InsertBgnUiState = InsertBgnUiState(
    insertBgnUiEvent = toInsertBgnUiEvent()
)
fun Bangunan.toInsertBgnUiEvent(): InsertBgnUiEvent = InsertBgnUiEvent(
    id_bangunan = id_bangunan,
    nama_bangunan = nama_bangunan,
    jumlah_lantai = jumlah_lantai,
    alamat = alamat
)

