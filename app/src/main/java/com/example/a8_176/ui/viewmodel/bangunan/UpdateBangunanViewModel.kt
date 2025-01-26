package com.example.a8_176.ui.viewmodel.bangunan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8_176.repository.BangunanRepository
import com.example.a8_176.ui.view.bangunan.DestinasiUpdateBgn
import kotlinx.coroutines.launch

class UpdateBangunanViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorybangunan: BangunanRepository
): ViewModel(){
    var updateBgnUiState by mutableStateOf(InsertBgnUiState())
        private set

    private val _id_bangunan: String = checkNotNull(savedStateHandle[DestinasiUpdateBgn.id_bangunan])

    init {
        viewModelScope.launch {
            updateBgnUiState = repositorybangunan.getBangunanbyId(_id_bangunan)
                .toBgnUiState()
        }
    }

    fun updateInsertBgnState(insertBgnUiEvent: InsertBgnUiEvent) {
        updateBgnUiState = InsertBgnUiState(insertBgnUiEvent = insertBgnUiEvent)
    }

    suspend fun updateBgn() {
        viewModelScope.launch {
            try {
                repositorybangunan.updateBangunan(_id_bangunan, updateBgnUiState.insertBgnUiEvent.toBgn())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}