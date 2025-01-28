package com.example.a8_176.ui.viewmodel.pembayaransewa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a8_176.model.Kamar
import com.example.a8_176.model.PembayaranSewa
import com.example.a8_176.repository.KamarRepository
import com.example.a8_176.repository.PembayaransewaRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomePsUiState{
    data class Succsess(val pembayaran: List<PembayaranSewa>): HomePsUiState()
    object Error : HomePsUiState()
    object Loading : HomePsUiState()
}

class HomePembayaranSewaViewModel(
    private val ps: PembayaransewaRepository
): ViewModel() {
    var psUIState: HomePsUiState by mutableStateOf(HomePsUiState.Loading)
        private set
    var isRefreshing by mutableStateOf(false)
        private set
    var snackbarMessage: String? by mutableStateOf(null)
        private set

    init {
        getPs()
    }

    fun getPs() {
        viewModelScope.launch {
            isRefreshing = true
            psUIState = try {
                HomePsUiState.Succsess(ps.getPembayaransewa())
            }catch (e: IOException) {
                HomePsUiState.Error
                snackbarMessage = "Network error: ${e.message}"
                HomePsUiState.Error
            }catch (e: HttpException) {
                HomePsUiState.Error
                snackbarMessage = "HTTP error: ${e.message}"
                HomePsUiState.Error
            } finally {
                isRefreshing = false
            }
        }
    }

    fun deletePs(id_pembayaran: String) {
        viewModelScope.launch {
            try {
                ps.deletePembayaransewa(id_pembayaran)
            } catch (e: IOException) {
                HomePsUiState.Error
            } catch (e: HttpException) {
                HomePsUiState.Error
            }
        }
    }
}