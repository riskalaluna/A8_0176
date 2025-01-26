package com.example.a8_176.ui.navigation

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a8_176.ui.view.DestinasiHomeUtama
import com.example.a8_176.ui.view.HomeUtamaView
import com.example.a8_176.ui.view.bangunan.DestinasiHomeBgn
import com.example.a8_176.ui.view.kamar.DestinasiHomeKmr
import com.example.a8_176.ui.view.mahasiswa.DestinasiHomeMhs
import com.example.a8_176.ui.view.pembayaransewa.DestinasiHomePs

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
    }
}

