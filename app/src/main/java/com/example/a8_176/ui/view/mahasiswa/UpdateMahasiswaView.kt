package com.example.a8_176.ui.view.mahasiswa

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
import com.example.a8_176.ui.view.kamar.EntryBodyKmr
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.mahasiswa.UpdateMahasiswaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateMhs: DestinasiNavigasi {
    override val route = "update/{id_mahasiswa}"
    override val titleRes = "Update Mahasiswa"
    const val id_mahasiswa = "id_mahasiswa"
    val routeWithArgs = "$route/{$id_mahasiswa}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMhsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateMahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiStateMhs = viewModel.updateMhsUiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiUpdateMhs.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodyMhs(
            modifier = Modifier.padding(padding),
            insertMhsUiState = viewModel.updateMhsUiState,
            onMhsValueChange = viewModel::updateInsertMhsState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.updateMhs()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            },
            uiState = viewModel.updateMhsUiState,
            idkamarList = viewModel.idkamarList
        )
    }
}