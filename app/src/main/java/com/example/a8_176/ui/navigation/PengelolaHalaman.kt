package com.example.a8_176.ui.navigation

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a8_176.ui.view.DestinasiHomeUtama
import com.example.a8_176.ui.view.HomeUtamaView
import com.example.a8_176.ui.view.bangunan.DestinasiDetailBgn
import com.example.a8_176.ui.view.bangunan.DestinasiEntryBgn
import com.example.a8_176.ui.view.bangunan.DestinasiHomeBgn
import com.example.a8_176.ui.view.bangunan.DestinasiUpdateBgn
import com.example.a8_176.ui.view.bangunan.DetailBgnScreen
import com.example.a8_176.ui.view.bangunan.EntryBgnScreen
import com.example.a8_176.ui.view.bangunan.HomeBgnScreen
import com.example.a8_176.ui.view.bangunan.UpdateBgnScreen
import com.example.a8_176.ui.view.kamar.DestinasiDetailKmr
import com.example.a8_176.ui.view.kamar.DestinasiEntryKmr
import com.example.a8_176.ui.view.kamar.DestinasiHomeKmr
import com.example.a8_176.ui.view.kamar.DestinasiUpdateKmr
import com.example.a8_176.ui.view.kamar.DetailKmrScreen
import com.example.a8_176.ui.view.kamar.EntryKmrScreen
import com.example.a8_176.ui.view.kamar.HomeKmrScreen
import com.example.a8_176.ui.view.kamar.UpdateKmrScreen
import com.example.a8_176.ui.view.mahasiswa.DestinasiDetailMhs
import com.example.a8_176.ui.view.mahasiswa.DestinasiEntryMhs
import com.example.a8_176.ui.view.mahasiswa.DestinasiHomeMhs
import com.example.a8_176.ui.view.mahasiswa.DestinasiUpdateMhs
import com.example.a8_176.ui.view.mahasiswa.DetailMhsScreen
import com.example.a8_176.ui.view.mahasiswa.EntryMhsScreen
import com.example.a8_176.ui.view.mahasiswa.HomeMhsScreen
import com.example.a8_176.ui.view.mahasiswa.UpdateMhsScreen
import com.example.a8_176.ui.view.pembayaransewa.DestinasiDetailPs
import com.example.a8_176.ui.view.pembayaransewa.DestinasiEntryPs
import com.example.a8_176.ui.view.pembayaransewa.DestinasiHomePs
import com.example.a8_176.ui.view.pembayaransewa.DestinasiUpdatePs
import com.example.a8_176.ui.view.pembayaransewa.DetailPsScreen
import com.example.a8_176.ui.view.pembayaransewa.EntryPsScreen
import com.example.a8_176.ui.view.pembayaransewa.HomePsScreen
import com.example.a8_176.ui.view.pembayaransewa.UpdatePsScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeUtama.route,
        modifier = Modifier,
    ) {
        composable(
            route = DestinasiHomeUtama.route
        ) {
            HomeUtamaView(
                onMahasiswa = {
                    navController.navigate(DestinasiHomeMhs.route)
                },
                onKamar = {
                    navController.navigate(DestinasiHomeKmr.route)
                },
                onBangunan = {
                    navController.navigate(DestinasiHomeBgn.route)
                },
                onPembayaranSewa = {
                    navController.navigate(DestinasiHomePs.route)
                },
            )
        }

        //BANGUNAN
        composable(DestinasiHomeBgn.route) {
            HomeBgnScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryBgn.route) },
                onDetailClick = { id_bangunan ->
                    navController.navigate("${DestinasiDetailBgn.route}/$id_bangunan")
                    println("PengelolaHalaman: id_bangunan $id_bangunan")
                },
                onBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}

