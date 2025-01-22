package com.example.a8_176.ui.viewmodel.mahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState{
    data class Succsess(val mahasiswa: List<Mahasiswa>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeMahasiswaViewModel(private val mhs: MahasiswaRepository): ViewModel() {
    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set
    var isRefreshing by mutableStateOf(false)
        private set
    var snackbarMessage: String? by mutableStateOf(null)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            isRefreshing = true
            mhsUIState = try {
                HomeUiState.Succsess(mhs.getMahasiswa())
            }catch (e: IOException) {
                HomeUiState.Error
                snackbarMessage = "Network error: ${e.message}"
                HomeUiState.Error
            }catch (e: HttpException) {
                HomeUiState.Error
                snackbarMessage = "HTTP error: ${e.message}"
                HomeUiState.Error
            } finally {
                isRefreshing = false
            }
        }
    }
}