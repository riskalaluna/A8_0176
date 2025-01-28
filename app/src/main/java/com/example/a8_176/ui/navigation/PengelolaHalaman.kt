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
                onBangunan = {
                    navController.navigate(DestinasiHomeBgn.route)
                },
                onKamar = {
                    navController.navigate(DestinasiHomeKmr.route)
                },
                onMahasiswa = {
                    navController.navigate(DestinasiHomeMhs.route)
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
                    navController.navigate(DestinasiHomeUtama.route) {
                        popUpTo(DestinasiHomeUtama.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(DestinasiEntryBgn.route) {
            EntryBgnScreen(navigateBack = {
                navController.navigate(DestinasiHomeBgn.route) {
                    popUpTo(DestinasiHomeBgn.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailBgn.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailBgn.id_bangunan) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_bangunan = it.arguments?.getString(DestinasiDetailBgn.id_bangunan)
            id_bangunan?.let { id_bangunan ->
                DetailBgnScreen(
                    id_bangunan = id_bangunan,
                    onBackClick = {
                        navController.navigate(DestinasiHomeBgn.route) {
                            popUpTo(DestinasiHomeBgn.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBgn.route}/$id_bangunan")
                    },
                )
            }
        }
        composable(
            "${DestinasiUpdateBgn.route}/{id_bangunan}",
            arguments = listOf(
                navArgument("id_bangunan") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_bangunan = backStackEntry.arguments?.getString("id_bangunan")
            id_bangunan?.let { id_bangunan ->
                UpdateBgnScreen(
                    onBack = {
                        navController.navigate(DestinasiHomeBgn.route) },
                    onNavigate = { navController.navigate(DestinasiHomeBgn.route) },
                )
            }
        }

        //KAMAR
        composable(DestinasiHomeKmr.route) {
            HomeKmrScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryKmr.route) },
                onDetailClick = { id_kamar ->
                    navController.navigate("${DestinasiDetailKmr.route}/$id_kamar")
                    println("PengelolaHalaman: id_kamar $id_kamar")
                },
                onBack = {
                    navController.navigate(DestinasiHomeUtama.route) {
                        popUpTo(DestinasiHomeUtama.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(DestinasiEntryKmr.route) {
            EntryKmrScreen(navigateBack = {
                navController.navigate(DestinasiHomeKmr.route) {
                    popUpTo(DestinasiHomeKmr.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailKmr.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailKmr.id_kamar) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_kamar = it.arguments?.getString(DestinasiDetailKmr.id_kamar)
            id_kamar?.let { id_kamar ->
                DetailKmrScreen(
                    id_kamar = id_kamar,
                    onBackClick = {
                        navController.navigate(DestinasiHomeKmr.route) {
                            popUpTo(DestinasiHomeKmr.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateKmr.route}/$id_kamar")
                    },
                )
            }
        }
        composable(
            "${DestinasiUpdateKmr.route}/{id_kamar}",
            arguments = listOf(
                navArgument("id_kamar") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_kamar = backStackEntry.arguments?.getString("id_kamar")
            id_kamar?.let { id_kamar ->
                UpdateKmrScreen(
                    onBack = {
                        navController.navigate(DestinasiHomeKmr.route) },
                    onNavigate = { navController.navigate(DestinasiHomeKmr.route) },
                )
            }
        }

        //MAHASISWA
        composable(DestinasiHomeMhs.route) {
            HomeMhsScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryMhs.route) },
                onDetailClick = { id_mahasiswa ->
                    navController.navigate("${DestinasiDetailMhs.route}/$id_mahasiswa")
                    println("PengelolaHalaman: id_mahasiswa $id_mahasiswa")
                },
                onBack = {
                    navController.navigate(DestinasiHomeUtama.route) {
                        popUpTo(DestinasiHomeUtama.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(DestinasiEntryMhs.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHomeMhs.route) {
                    popUpTo(DestinasiHomeMhs.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailMhs.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailMhs.id_mahasiswa) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_mahasiswa = it.arguments?.getString(DestinasiDetailMhs.id_mahasiswa)
            id_mahasiswa?.let { id_mahasiswa ->
                DetailMhsScreen(
                    id_mahasiswa = id_mahasiswa,
                    onBackClick = {
                        navController.navigate(DestinasiHomeMhs.route) {
                            popUpTo(DestinasiHomeMhs.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMhs.route}/$id_mahasiswa")
                    },
                )
            }
        }
        composable(
            "${DestinasiUpdateMhs.route}/{id_mahasiswa}",
            arguments = listOf(
                navArgument("id_mahasiswa") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_mahasiswa = backStackEntry.arguments?.getString("id_mahasiswa")
            id_mahasiswa?.let { id_mahasiswa ->
                UpdateMhsScreen(
                    onBack = {
                        navController.navigate(DestinasiHomeMhs.route) },
                    onNavigate = { navController.navigate(DestinasiHomeMhs.route) },
                )
            }
        }

        //PEMBAYARANSEWA
        composable(DestinasiHomePs.route) {
            HomePsScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPs.route) },
                onDetailClick = { id_pembayaran ->
                    navController.navigate("${DestinasiDetailPs.route}/$id_pembayaran")
                    println("PengelolaHalaman: id_pembayaran $id_pembayaran")
                },
                onBack = {
                    navController.navigate(DestinasiHomeUtama.route) {
                        popUpTo(DestinasiHomeUtama.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(DestinasiEntryPs.route) {
            EntryPsScreen(navigateBack = {
                navController.navigate(DestinasiHomePs.route) {
                    popUpTo(DestinasiHomePs.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailPs.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailPs.id_pembayaran) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_pembayaran = it.arguments?.getString(DestinasiDetailPs.id_pembayaran)
            id_pembayaran?.let { id_pembayaran ->
                DetailPsScreen(
                    id_pembayaran = id_pembayaran,
                    onBackClick = {
                        navController.navigate(DestinasiHomePs.route) {
                            popUpTo(DestinasiHomePs.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePs.route}/$id_pembayaran")
                    },
                )
            }
        }
        composable(
            "${DestinasiUpdatePs.route}/{id_pembayaran}",
            arguments = listOf(
                navArgument("id_pembayaran") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_pembayaran = backStackEntry.arguments?.getString("id_pembayaran")
            id_pembayaran?.let { id_pembayaran ->
                UpdatePsScreen(
                    onBack = {
                        navController.navigate(DestinasiHomePs.route) },
                    onNavigate = { navController.navigate(DestinasiHomePs.route) },
                )
            }
        }
    }
}

