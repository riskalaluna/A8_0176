package com.example.a8_176.ui.view.kamar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.model.Bangunan
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.custumwidget.DropDownAsrama
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.kamar.InsertKamarViewModel
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiEvent
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiState
import kotlinx.coroutines.launch

object DestinasiEntryKmr : DestinasiNavigasi {
    override val route = "item_entry_kmr"
    override val titleRes = "Tambah Data Kamar"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKmrScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateKamar = viewModel.uiStateKamar
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiEntryKmr.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyKmr(
            uiState = uiStateKamar,
            idbangunanList = uiStateKamar.idbangunanList,
            insertKmrUiState = viewModel.uiStateKamar,
            onKamarValueChange = viewModel::updateInsertKmrState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.insertKmr()
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
fun EntryBodyKmr(
    insertKmrUiEvent: InsertKmrUiEvent = InsertKmrUiEvent(),
    insertKmrUiState: InsertKmrUiState = InsertKmrUiState(),
    onKamarValueChange: (InsertKmrUiEvent) -> Unit,
    onSavedClick: () -> Unit,
    uiState: InsertKmrUiState,
    modifier: Modifier = Modifier,
    idbangunanList: List<Bangunan>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertKmrUiEvent = insertKmrUiState.insertKmrUiEvent,
            onValueChange = onKamarValueChange,
            modifier = Modifier.fillMaxWidth(),
            idbangunanList = idbangunanList
        )
        Button(
            onClick = onSavedClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5),
                contentColor = Color.White)
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertKmrUiEvent: InsertKmrUiEvent = InsertKmrUiEvent(),
    modifier: Modifier = Modifier,
    onValueChange: (InsertKmrUiEvent) -> Unit = {},
    enabled: Boolean = true,
    idbangunanList: List<Bangunan>,
) {
    val status_kamar = listOf("Terisi", "Kosong")

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val inputFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF42A5F5),
            unfocusedBorderColor = Color(0xFF90CAF9),
            cursorColor = Color(0xFF42A5F5),
            focusedLabelColor = Color(0xFF42A5F5),
            unfocusedLabelColor = Color(0xFF1976D2)
        )

        OutlinedTextField(
            value = insertKmrUiEvent.id_kamar,
            onValueChange = {onValueChange(insertKmrUiEvent.copy(id_kamar = it))},
            label = { Text("ID Kamar") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertKmrUiEvent.nomor_kamar,
            onValueChange = {onValueChange(insertKmrUiEvent.copy(nomor_kamar = it))},
            label = { Text("Nomor Kamar") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )

        DropDownAsrama(
            label = "ID Bangunan",
            options = idbangunanList.map { it.id_bangunan },
            selectedOption = idbangunanList.find { it.id_bangunan == insertKmrUiEvent.id_bangunan }?.id_bangunan.orEmpty(),
            onOptionSelected = { selected ->
                val selectedBangunan = idbangunanList.find { it.id_bangunan == selected }
                onValueChange(insertKmrUiEvent.copy(id_bangunan = selectedBangunan?.id_bangunan ?: ""))
            },
            colors = inputFieldColors
        )

        OutlinedTextField(
            value = insertKmrUiEvent.kapasitas,
            onValueChange = {onValueChange(insertKmrUiEvent.copy(kapasitas = it))},
            label = { Text("Kapasitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )

        Text(text = "Status Kamar", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            status_kamar.forEach { st ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    RadioButton(
                        selected = insertKmrUiEvent.status_kamar == st,
                        onClick = {
                            onValueChange(insertKmrUiEvent.copy(status_kamar = st))
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF42A5F5))
                    )
                    Text(
                        text = st,
                        color = Color(0xFF1976D2),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
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