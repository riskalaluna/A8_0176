package com.example.a8_176.ui.view.bangunan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a8_176.ui.viewmodel.bangunan.InsertBgnUiEvent


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
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertBgnUiEvent.id_bangunan,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(id_bangunan = it))},
            label = { Text("ID Bangunan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertBgnUiEvent.nama_bangunan,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(nama_bangunan = it))},
            label = { Text("Nama Bangunan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertBgnUiEvent.jumlah_lantai,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(jumlah_lantai = it))},
            label = { Text("Jumlah Lantai") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertBgnUiEvent.alamat,
            onValueChange = {onValueChange(insertBgnUiEvent.copy(alamat = it))},
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if(enabled) {
            Text("Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}