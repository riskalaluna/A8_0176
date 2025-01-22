package com.example.a8_176.ui.viewmodel.mahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertMahasiswaViewModel(private val mhs: MahasiswaRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun insertMhs() {
        viewModelScope.launch {
            try {
                mhs.insertMahasiswa(uiState.insertUiEvent.toMhs())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)
data class InsertUiEvent(
    val id_mahasiswa: String = "",
    val nama_mahasiswa: String = "",
    val nomor_identitas: String = "",
    val email: String = "",
    val nomor_telepon: String = "",
    val id_kamar: String = "",
)

fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)
fun Mahasiswa.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_mahasiswa = id_mahasiswa,
    nama_mahasiswa = nama_mahasiswa,
    nomor_identitas = nomor_identitas,
    email = email,
    nomor_telepon = nomor_telepon,
    id_kamar = id_kamar
)

