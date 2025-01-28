package com.example.a8_176.ui.view.mahasiswa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.model.Kamar
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.custumwidget.DropDownAsrama
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.mahasiswa.InsertMahasiswaViewModel
import com.example.a8_176.ui.viewmodel.mahasiswa.InsertMhsUiEvent
import com.example.a8_176.ui.viewmodel.mahasiswa.InsertMhsUiState
import kotlinx.coroutines.launch

object DestinasiEntryMhs : DestinasiNavigasi {
    override val route = "item_entry_mhs"
    override val titleRes = "Tambah Data Mahasiswa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryMhsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertMahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateMhs = viewModel.uiStateMhs
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiEntryMhs.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyMhs(
            uiState = uiStateMhs,
            idkamarList = uiStateMhs.idkamarList,
            insertMhsUiState = viewModel.uiStateMhs,
            onMhsValueChange = viewModel::updateInsertMhsState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.insertMhs()
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
fun EntryBodyMhs(
    insertMhsUiEvent: InsertMhsUiEvent = InsertMhsUiEvent(),
    insertMhsUiState: InsertMhsUiState,
    onMhsValueChange: (InsertMhsUiEvent) -> Unit,
    onSavedClick: () -> Unit,
    uiState: InsertMhsUiState,
    modifier: Modifier = Modifier,
    idkamarList: List<Kamar>,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInputMhs(
            insertMhsUiEvent = insertMhsUiState.insertMhsUiEvent,
            onValueChange = onMhsValueChange,
            modifier = Modifier.fillMaxWidth(),
            idkamarList = idkamarList
        )
        Button(
            onClick = onSavedClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputMhs(
    insertMhsUiEvent: InsertMhsUiEvent = InsertMhsUiEvent(),
    modifier: Modifier = Modifier,
    onValueChange: (InsertMhsUiEvent) -> Unit = {},
    enabled: Boolean = true,
    idkamarList: List<Kamar>,
) {
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
            value = insertMhsUiEvent.id_mahasiswa,
            onValueChange = {onValueChange(insertMhsUiEvent.copy(id_mahasiswa = it))},
            label = { Text("ID Mahasiswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertMhsUiEvent.nama_mahasiswa,
            onValueChange = {onValueChange(insertMhsUiEvent.copy(nama_mahasiswa = it))},
            label = { Text("Nama Mahasiswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertMhsUiEvent.nomor_identitas,
            onValueChange = {onValueChange(insertMhsUiEvent.copy(nomor_identitas = it))},
            label = { Text("Nomor Identitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertMhsUiEvent.email,
            onValueChange = {onValueChange(insertMhsUiEvent.copy(email = it))},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertMhsUiEvent.nomor_telepon,
            onValueChange = {onValueChange(insertMhsUiEvent.copy(nomor_telepon = it))},
            label = { Text("Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        DropDownAsrama(
            label = "ID Kamar",
            options = idkamarList.map { it.id_kamar },
            selectedOption = idkamarList.find { it.id_kamar == insertMhsUiEvent.id_kamar }?.id_kamar.orEmpty(),
            onOptionSelected = { selected ->
                val selectedKamar = idkamarList.find { it.id_kamar == selected }
                onValueChange(insertMhsUiEvent.copy(id_kamar = selectedKamar?.id_kamar ?: ""))
            },
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
            modifier = Modifier.padding(12.dp)
        )
    }
}