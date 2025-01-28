package com.example.a8_176.ui.view.bangunan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.bangunan.InsertBangunanViewModel
import com.example.a8_176.ui.viewmodel.bangunan.InsertBgnUiEvent
import com.example.a8_176.ui.viewmodel.bangunan.InsertBgnUiState
import kotlinx.coroutines.launch

object DestinasiEntryBgn : DestinasiNavigasi {
    override val route = "item_entry_bgn"
    override val titleRes = "Tambah Data Bangunan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBgnScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBangunanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiEntryBgn.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
            )
        }
    ) { innerPadding ->
        EntryBodyBgn(
            insertBgnUiState = viewModel.uiState,
            onBangunanValueChange = viewModel::updateInsertBgnState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.insertBgn()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBodyBgn(
    insertBgnUiState: InsertBgnUiState,
    onBangunanValueChange: (InsertBgnUiEvent) -> Unit,
    onSavedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertBgnUiEvent = insertBgnUiState.insertBgnUiEvent,
            onValueChange = onBangunanValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSavedClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5), contentColor = Color.White)
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertBgnUiEvent: InsertBgnUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertBgnUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        val inputFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF42A5F5), // Warna border saat fokus
            unfocusedBorderColor = Color(0xFF90CAF9), // Warna border saat tidak fokus
            cursorColor = Color(0xFF42A5F5), // Warna kursor
            focusedLabelColor = Color(0xFF42A5F5), // Warna label saat fokus
            unfocusedLabelColor = Color(0xFF1976D2) // Warna label saat tidak fokus
        )

        OutlinedTextField(
            value = insertBgnUiEvent.id_bangunan,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(id_bangunan = it))},
            label = { Text("ID Bangunan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertBgnUiEvent.nama_bangunan,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(nama_bangunan = it))},
            label = { Text("Nama Bangunan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertBgnUiEvent.jumlah_lantai,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(jumlah_lantai = it))},
            label = { Text("Jumlah Lantai") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertBgnUiEvent.alamat,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(alamat = it))},
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        if(enabled) {
            Text("Isi Semua Data!",
                modifier = Modifier.padding(12.dp),
                color = Color.Red
            )
        }
        Divider(
            thickness = 8.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(12.dp)
        )
    }
}
