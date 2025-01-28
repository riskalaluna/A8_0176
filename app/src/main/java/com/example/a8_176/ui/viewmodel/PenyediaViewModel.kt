package com.example.a8_176.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a8_176.AsramaApplications
import com.example.a8_176.ui.viewmodel.bangunan.DetailBangunanViewModel
import com.example.a8_176.ui.viewmodel.bangunan.HomeBangunanViewModel
import com.example.a8_176.ui.viewmodel.bangunan.InsertBangunanViewModel
import com.example.a8_176.ui.viewmodel.bangunan.UpdateBangunanViewModel
import com.example.a8_176.ui.viewmodel.kamar.DetailKamarViewModel
import com.example.a8_176.ui.viewmodel.kamar.HomeKamarViewModel
import com.example.a8_176.ui.viewmodel.kamar.InsertKamarViewModel
import com.example.a8_176.ui.viewmodel.kamar.UpdateKamarViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        //Bangunan
        initializer { HomeBangunanViewModel(aplikasiKontak().container.kontakBgnRepository) }
        initializer { InsertBangunanViewModel(aplikasiKontak().container.kontakBgnRepository) }
        initializer { DetailBangunanViewModel(aplikasiKontak().container.kontakBgnRepository) }
        initializer { UpdateBangunanViewModel(createSavedStateHandle(),aplikasiKontak().container.kontakBgnRepository) }

        //kamar
        initializer { HomeKamarViewModel(aplikasiKontak().container.kontakKmrRepository) }
        initializer { InsertKamarViewModel(
            aplikasiKontak().container.kontakKmrRepository,
            aplikasiKontak().container.kontakBgnRepository
        ) }
        initializer { DetailKamarViewModel(aplikasiKontak().container.kontakKmrRepository) }
        initializer { UpdateKamarViewModel(createSavedStateHandle(),
            aplikasiKontak().container.kontakKmrRepository,
            aplikasiKontak().container.kontakBgnRepository
        ) }
    }
}
fun CreationExtras.aplikasiKontak(): AsramaApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AsramaApplications)