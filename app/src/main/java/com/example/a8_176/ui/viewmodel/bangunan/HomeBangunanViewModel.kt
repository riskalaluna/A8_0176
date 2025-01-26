package com.example.a8_176.ui.viewmodel.bangunan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.a8_176.model.Bangunan
import com.example.a8_176.repository.BangunanRepository

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

}