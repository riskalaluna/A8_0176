package com.example.a8_176.ui.view.bangunan

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
import com.example.a8_176.ui.viewmodel.bangunan.UpdateBangunanViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateBgn: DestinasiNavigasi {
    override val route = "updatebgn"
    override val titleRes = "Edit Bangunan"
    const val id_bangunan = "id_bangunan"
    val routeWithArgs = "$route/{$id_bangunan}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBgnScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateBangunanViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiUpdateBgn.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodyBgn(
            modifier = Modifier.padding(padding),
            insertBgnUiState = viewModel.updateBgnUiState,
            onBangunanValueChange = viewModel::updateInsertBgnState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.updateBgn()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}