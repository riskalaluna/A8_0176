package com.example.a8_176.ui.view.kamar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.kamar.UpdateKamarViewModel
import com.example.a8_176.ui.viewmodel.mahasiswa.UpdateMahasiswaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKmrScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiStateKamar = viewModel.updateKmrUiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiUpdateKmr.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodyKmr(
            modifier = Modifier.padding(padding),
            insertKmrUiState = viewModel.updateKmrUiState,
            onKamarValueChange = viewModel::updateInsertKmrState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.updateKmr()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            },
            uiState = viewModel.updateKmrUiState,
            idbangunanList = viewModel.idbangunanList
        )
    }
}