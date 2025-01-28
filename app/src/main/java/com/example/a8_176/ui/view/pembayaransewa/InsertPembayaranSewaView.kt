package com.example.a8_176.ui.view.pembayaransewa


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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.model.Mahasiswa
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.custumwidget.DatePickerFields
import com.example.a8_176.ui.custumwidget.DropDownAsrama
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.pembayaransewa.InsertPembayaranSewaViewModel
import com.example.a8_176.ui.viewmodel.pembayaransewa.InsertPsUiEvent
import com.example.a8_176.ui.viewmodel.pembayaransewa.InsertPsUiState
import kotlinx.coroutines.launch

object DestinasiEntryPs : DestinasiNavigasi {
    override val route = "item_entry_ps"
    override val titleRes = "Tambah Pembayaran Sewa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPembayaranSewaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStatePembayaranSewa = viewModel.uiStatePembayaranSewa
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiEntryPs.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyPs(
            uiState = uiStatePembayaranSewa,
            idmahasiswaList = uiStatePembayaranSewa.idmahasiswaList,
            insertPsUiState = viewModel.uiStatePembayaranSewa,
            onValueChange = viewModel::updateInsertPsState,
            onSavedClick = {
                coroutineScope.launch {
                    viewModel.insertPs()
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
fun EntryBodyPs(
    insertPsUiEvent: InsertPsUiEvent = InsertPsUiEvent(),
    insertPsUiState: InsertPsUiState = InsertPsUiState(),
    onValueChange: (InsertPsUiEvent) -> Unit,
    onSavedClick: () -> Unit,
    uiState: InsertPsUiState,
    modifier: Modifier = Modifier,
    idmahasiswaList: List<Mahasiswa>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPs(
            insertPsUiEvent = insertPsUiState.insertPsUiEvent,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            idmahasiswaList = idmahasiswaList
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
fun FormInputPs(
    insertPsUiEvent: InsertPsUiEvent = InsertPsUiEvent(),
    modifier: Modifier = Modifier,
    onValueChange: (InsertPsUiEvent) -> Unit = {},
    enabled: Boolean = true,
    idmahasiswaList: List<Mahasiswa>,
) {
    val status_pembayaran = listOf("Lunas", "Belum Lunas")
    var tanggal_pembayaran by remember { mutableStateOf(insertPsUiEvent.tanggal_pembayaran)  }

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
            value = insertPsUiEvent.id_pembayaran,
            onValueChange = {onValueChange(insertPsUiEvent.copy(id_pembayaran = it))},
            label = { Text("ID Pembayaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        DropDownAsrama(
            label = "ID Mahasiswa",
            options = idmahasiswaList.map { it.id_mahasiswa },
            selectedOption = idmahasiswaList.find { it.id_mahasiswa == insertPsUiEvent.id_mahasiswa }?.id_mahasiswa.orEmpty(),
            onOptionSelected = { selected ->
                val selectedMahasiswa = idmahasiswaList.find { it.id_mahasiswa == selected }
                onValueChange(insertPsUiEvent.copy(id_mahasiswa = selectedMahasiswa?.id_mahasiswa ?: ""))
            },
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertPsUiEvent.jumlah,
            onValueChange = {onValueChange(insertPsUiEvent.copy(jumlah = it))},
            label = { Text("Jumlah Pembayaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        DatePickerFields(
            label = "Tanggal Pembayaran",
            selectedDate = tanggal_pembayaran,
            onDateSelected = { selectedDate ->
                tanggal_pembayaran = selectedDate
                onValueChange(insertPsUiEvent.copy(tanggal_pembayaran = selectedDate))
            },
            colors = inputFieldColors
        )
        Text(text = "Status Pembayaran", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            status_pembayaran.forEach { sp ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    RadioButton(
                        selected = insertPsUiEvent.status_pembayaran == sp,
                        onClick = {
                            onValueChange(insertPsUiEvent.copy(status_pembayaran = sp))
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF42A5F5))
                    )
                    Text(
                        text = sp,
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