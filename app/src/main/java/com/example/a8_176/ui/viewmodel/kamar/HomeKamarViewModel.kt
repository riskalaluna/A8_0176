package com.example.a8_176.ui.viewmodel.kamar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a8_176.model.Kamar
import com.example.a8_176.repository.KamarRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeKmrUiState{
    data class Succsess(val kamar: List<Kamar>): HomeKmrUiState()
    object Error : HomeKmrUiState()
    object Loading : HomeKmrUiState()
}

class HomeKamarViewModel(
    private val kmr: KamarRepository
): ViewModel() {
    var kmrUIState: HomeKmrUiState by mutableStateOf(HomeKmrUiState.Loading)
        private set
    var isRefreshing by mutableStateOf(false)
        private set
    var snackbarMessage: String? by mutableStateOf(null)
        private set

    init {
        getKmr()
    }

    fun getKmr() {
        viewModelScope.launch {
            isRefreshing = true
            kmrUIState = try {
                HomeKmrUiState.Succsess(kmr.getKamar())
            }catch (e: IOException) {
                HomeKmrUiState.Error
                snackbarMessage = "Network error: ${e.message}"
                HomeKmrUiState.Error
            }catch (e: HttpException) {
                HomeKmrUiState.Error
                snackbarMessage = "HTTP error: ${e.message}"
                HomeKmrUiState.Error
            } finally {
                isRefreshing = false
            }
        }
    }

    fun deleteKmr(id_kamar: String) {
        viewModelScope.launch {
            try {
                kmr.deleteKamar(id_kamar)
            } catch (e: IOException) {
                HomeKmrUiState.Error
            } catch (e: HttpException) {
                HomeKmrUiState.Error
            }
        }
    }
}