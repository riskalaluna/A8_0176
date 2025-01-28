package com.example.a8_176.ui.view.kamar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.kamar.DetailKamarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKmrScreen(
    id_kamar: String,
    onEditClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val kamar = viewModel.uiState.detailKmrUiEvent

    LaunchedEffect(id_kamar) {
        viewModel.fetchDetailKamar(id_kamar)
    }

    val isLoading = viewModel.uiState.isLoading
    val isError = viewModel.uiState.isError
    val errorMessage = viewModel.uiState.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(DestinasiDetailKmr.titleRes,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Blue
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { paddingValues ->
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Blue
                    )
                } else if (isError) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else if (viewModel.uiState.isUiEventNotEmpty) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = "ID Kamar: ${kamar.id_kamar}",
                                    style = MaterialTheme.typography.bodyLarge, color = Color.Blue)
                                Text(text = "Nomor Kamar: ${kamar.nomor_kamar}",
                                    style = MaterialTheme.typography.bodyLarge, color = Color.Blue)
                                Text(text = "ID Bangunan: ${kamar.id_bangunan}",
                                    style = MaterialTheme.typography.bodyLarge, color = Color.Blue)
                                Text(text = "Kapasitas: ${kamar.kapasitas}",
                                    style = MaterialTheme.typography.bodyLarge, color = Color.Blue)
                                Text(text = "Status Kamar: ${kamar.status_kamar}",
                                    style = MaterialTheme.typography.bodyLarge, color = Color.Blue)
                            }
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Button(onClick = {onEditClick(kamar.id_kamar)},
                                colors = ButtonDefaults.buttonColors(containerColor =Color(0xFF42A5F5), contentColor = Color.White)
                            ) {
                            Text("Edit Data")
                            }
                        }
                    }
                }
            }
        }
    )
}