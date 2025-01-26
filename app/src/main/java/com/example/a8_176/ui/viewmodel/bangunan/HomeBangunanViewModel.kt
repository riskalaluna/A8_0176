package com.example.a8_176.ui.viewmodel.bangunan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a8_176.model.Bangunan
import com.example.a8_176.repository.BangunanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeBgnUiState{
    data class Succsess(val bangunan: List<Bangunan>): HomeBgnUiState()
    object Error : HomeBgnUiState()
    object Loading : HomeBgnUiState()
}

class HomeBangunanViewModel(
    private val bgn: BangunanRepository
): ViewModel() {
    var bgnUIState: HomeBgnUiState by mutableStateOf(HomeBgnUiState.Loading)
        private set
    var isRefreshing by mutableStateOf(false)
        private set
    var snackbarMessage: String? by mutableStateOf(null)
        private set

    init {
        getBgn()
    }

    fun getBgn() {
        viewModelScope.launch {
            isRefreshing = true
            bgnUIState = try {
                HomeBgnUiState.Succsess(bgn.getBangunan())
            }catch (e: IOException) {
                HomeBgnUiState.Error
                snackbarMessage = "Network error: ${e.message}"
                HomeBgnUiState.Error
            }catch (e: HttpException) {
                HomeBgnUiState.Error
                snackbarMessage = "HTTP error: ${e.message}"
                HomeBgnUiState.Error
            } finally {
                isRefreshing = false
            }
        }
    }

    fun deleteBgn(id_bangunan: String) {
        viewModelScope.launch {
            try {
                bgn.deleteBangunan(id_bangunan)
            } catch (e: IOException) {
                HomeBgnUiState.Error
            } catch (e: HttpException) {
                HomeBgnUiState.Error
            }
        }
    }
}

