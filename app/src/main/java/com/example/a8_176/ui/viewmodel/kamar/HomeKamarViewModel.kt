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
}