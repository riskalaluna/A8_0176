package com.example.a8_176.ui.view.bangunan

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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.a8_176.R
import com.example.a8_176.model.Bangunan
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.navigation.DestinasiNavigasi
import com.example.a8_176.ui.view.mahasiswa.OnError
import com.example.a8_176.ui.view.mahasiswa.OnLoading
import com.example.a8_176.ui.viewmodel.PenyediaViewModel
import com.example.a8_176.ui.viewmodel.bangunan.HomeBangunanViewModel
import com.example.a8_176.ui.viewmodel.bangunan.HomeBgnUiState
import com.example.a8_176.ui.viewmodel.kamar.HomeKamarViewModel
import com.example.a8_176.ui.viewmodel.kamar.HomeKmrUiState

object DestinasiHomeBgn : DestinasiNavigasi {
    override val route = "homebgn"
    override val titleRes = "Home Bangunan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBgnScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    onAddBgn: () -> Unit = {},
    onBack: () -> Unit = {},
    viewModel: HomeBangunanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustumeTopAppBar(
                title = DestinasiHomeBgn.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                modifier = modifier,
                onRefresh = { viewModel.getBgn() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                //onClick = onAddBgn,
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Kontak"
                )
            }
        }
    ) { innerPadding ->
        HomeStatus(
            homeBgnUiState = viewModel.bgnUIState,
            retryAction = { viewModel.getBgn() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteBgn(it.id_bangunan)
                viewModel.getBgn()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeBgnUiState: HomeBgnUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Bangunan) -> Unit = {},
    onDetailClick: (String) -> Unit // Ubah tipe parameter ke Int
) {
    when (homeBgnUiState) {
        is HomeBgnUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeBgnUiState.Succsess ->
            if (homeBgnUiState.bangunan.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Kontak")
                }
            } else {
                BgnLayout(
                    bangunan = homeBgnUiState.bangunan,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_bangunan) // Kirim id_bangunan sebagai Int
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeBgnUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun BgnLayout(
    bangunan: List<Bangunan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Bangunan) -> Unit,
    onDeleteClick: (Bangunan) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bangunan) { kontak ->
            BgnCard(
                bangunan = kontak,
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
fun BgnCard(
    bangunan: Bangunan,
    modifier: Modifier = Modifier,
    onDeleteClick: (Bangunan) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bangunan.nama_bangunan,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(bangunan) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = bangunan.id_bangunan.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = bangunan.alamat,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = bangunan.jumlah_lantai,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

