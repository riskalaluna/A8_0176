package com.example.a8_176.ui.view.pembayaransewa

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.model.PembayaranSewa
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.kamar.HomeKamarViewModel
import com.example.a8_176.ui.viewmodel.pembayaransewa.HomePembayaranSewaViewModel

import com.example.a8_176.ui.viewmodel.pembayaransewa.HomePsUiState

object DestinasiHomePs : DestinasiNavigasi {
    override val route = "homeps"
    override val titleRes = "Home Pembayaran Sewa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePsScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    onBack: () -> Unit = {},
    viewModel: HomePembayaranSewaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiHomePs.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                modifier = modifier,
                onRefresh = { viewModel.getPs()},
                navigateUp = { onBack() },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                containerColor = Color(0xFF42A5F5),
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Pembayaran Sewa",
                    tint = Color.White
                )
            }
        },
    ){ innerPadding ->
        HomeStatusPs(
            homePsUiState = viewModel.psUIState,
            retryAction = { viewModel.getPs() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick, onDeleteClick = {
                viewModel.deletePs(it.id_pembayaran )
                viewModel.getPs()
            }
        )
    }
}

@Composable
fun HomeStatusPs(
    homePsUiState: HomePsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (PembayaranSewa) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when(homePsUiState) {
        is HomePsUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomePsUiState.Succsess ->
            if (homePsUiState.pembayaran.isEmpty()) {
                return Box (
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Tidak ada data Kontak")
                }
            } else {
                PsLayout(
                    pembayaranSewa = homePsUiState.pembayaran, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_pembayaran)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomePsUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.CircularProgressIndicator(
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun OnError(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Error Icon",
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Failed to load data. Please try again.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}


@Composable
fun PsLayout(
    pembayaranSewa: List<PembayaranSewa>,
    modifier: Modifier = Modifier,
    onDetailClick: (PembayaranSewa) -> Unit,
    onDeleteClick: (PembayaranSewa) -> Unit = {}
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pembayaranSewa) { kontak ->
            PsCard(
                pembayaranSewa = kontak,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kontak) },
                onDeleteClick = {
                    onDeleteClick(kontak)
                }
            )
        }
    }
}

@Composable
fun PsCard(
    pembayaranSewa: PembayaranSewa,
    modifier: Modifier = Modifier,
    onDeleteClick: (PembayaranSewa) -> Unit = {}
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = pembayaranSewa.status_pembayaran,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1976D2),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(pembayaranSewa)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = pembayaranSewa.id_pembayaran,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1976D2),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = pembayaranSewa.id_mahasiswa,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color(0xFF1976D2),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pembayaranSewa.jumlah,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color(0xFF1976D2),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}