package com.example.a8_176.ui.view.pembayaransewa

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
import com.example.a8_176.ui.view.pembayaransewa.EntryBodyPs
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.kamar.UpdateKamarViewModel
import com.example.a8_176.ui.viewmodel.mahasiswa.UpdateMahasiswaViewModel
import com.example.a8_176.ui.viewmodel.pembayaransewa.UpdatePembayaranSewaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdatePs: DestinasiNavigasi {
    override val route = "updateps"
    override val titleRes = "Update Data Pembayaran"
    const val id_pembayaran = "id_pembayaran"
    val routesWithArg = "$route/{$id_pembayaran}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdatePembayaranSewaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiStatePembayaranSewa = viewModel.updatePsUiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiUpdatePs.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodyPs(
            modifier = Modifier.padding(padding),
            insertPsUiState = viewModel.updatePsUiState,
            onValueChange = viewModel::updateInsertPsState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.updatePs()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            },
            uiState = viewModel.updatePsUiState,
            idmahasiswaList = viewModel.idmahasiswaList
        )
    }
}