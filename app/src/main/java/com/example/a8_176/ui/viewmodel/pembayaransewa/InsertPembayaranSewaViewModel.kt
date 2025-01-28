package com.example.a8_176.ui.viewmodel.pembayaransewa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.model.PembayaranSewa
import com.example.a8_176.repository.MahasiswaRepository
import com.example.a8_176.repository.PembayaransewaRepository
import kotlinx.coroutines.launch

class InsertPembayaranSewaViewModel(
    private val ps: PembayaransewaRepository,
    private val mhs: MahasiswaRepository
) : ViewModel() {
    var uiStatePembayaranSewa by mutableStateOf(InsertPsUiState())
        private set

    fun updateInsertPsState(insertPsUiEvent: InsertPsUiEvent) {
        uiStatePembayaranSewa = uiStatePembayaranSewa.copy(insertPsUiEvent = insertPsUiEvent)
    }

    init {
        loadMahasiswa()
    }

    private fun loadMahasiswa() {
        viewModelScope.launch {
            try {
                val mahasiswaList = mhs.getMahasiswa()
                uiStatePembayaranSewa = uiStatePembayaranSewa.copy(idmahasiswaList = mahasiswaList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Fungsi untuk menyimpan data pembayaran sewa ke repository
    fun insertPs() {
        viewModelScope.launch {
            try {
                ps.insertPembayaransewa(uiStatePembayaranSewa.insertPsUiEvent.toPs())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPsUiState(
    val insertPsUiEvent: InsertPsUiEvent = InsertPsUiEvent(),
    val idmahasiswaList: List<Mahasiswa> = emptyList()
)
data class InsertPsUiEvent(
    val id_pembayaran: String = "",
    val id_mahasiswa: String = "",
    val jumlah: String = "",
    val tanggal_pembayaran: String = "",
    val status_pembayaran:  String = "",
)

fun InsertPsUiEvent.toPs(): PembayaranSewa = PembayaranSewa(
    id_pembayaran = id_pembayaran,
    id_mahasiswa = id_mahasiswa,
    jumlah = jumlah,
    tanggal_pembayaran = tanggal_pembayaran,
    status_pembayaran = status_pembayaran
)

fun PembayaranSewa.toPsUiState(): InsertPsUiState = InsertPsUiState(
    insertPsUiEvent = toInsertPsUiEvent()
)
fun PembayaranSewa.toInsertPsUiEvent(): InsertPsUiEvent = InsertPsUiEvent(
    id_pembayaran = id_pembayaran,
    id_mahasiswa = id_mahasiswa,
    jumlah = jumlah,
    tanggal_pembayaran = tanggal_pembayaran,
    status_pembayaran = status_pembayaran
)