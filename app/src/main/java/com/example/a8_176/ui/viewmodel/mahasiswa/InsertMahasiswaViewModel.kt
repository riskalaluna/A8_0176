package com.example.a8_176.ui.viewmodel.mahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Bangunan
import com.example.a8_176.model.Kamar
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.repository.MahasiswaRepository
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiEvent
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiState
import com.example.a8_176.ui.viewmodel.kamar.toInsertKmrUiEvent
import com.example.a8_176.ui.viewmodel.kamar.toKmr
import kotlinx.coroutines.launch

class InsertMahasiswaViewModel(
    private val mhs: MahasiswaRepository,
    private val kmr: KamarRepository //mengambil KamarRepository (salah commit file)
): ViewModel() {
    var uiStateMhs by mutableStateOf(InsertMhsUiState())
        private set

    fun updateInsertMhsState(insertMhsUiEvent: InsertMhsUiEvent) {
        uiStateMhs = uiStateMhs.copy(insertMhsUiEvent = insertMhsUiEvent)
    }
    init {
        loadKamar()
    }

    private fun loadKamar() {
        viewModelScope.launch {
            try {
                val kamarList = kmr.getKamar()
                uiStateMhs = uiStateMhs.copy(idkamarList = kamarList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertMhs() {
        viewModelScope.launch {
            try {
                mhs.insertMahasiswa(uiStateMhs.insertMhsUiEvent.toMhs())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertMhsUiState(
    val insertMhsUiEvent: InsertMhsUiEvent = InsertMhsUiEvent(),
    val idkamarList: List<Kamar> = emptyList()
)
data class InsertMhsUiEvent(
    val id_mahasiswa: String = "",
    val nama_mahasiswa: String = "",
    val nomor_identitas: String = "",
    val email: String = "",
    val nomor_telepon: String = "",
    val id_kamar: String = "",
)

fun InsertMhsUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

fun Mahasiswa.toMhsUiState(): InsertMhsUiState = InsertMhsUiState(
    insertMhsUiEvent = toInsertMhsUiEvent()
)

fun Mahasiswa.toInsertMhsUiEvent(): InsertMhsUiEvent = InsertMhsUiEvent(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

