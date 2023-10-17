package com.olivia.plant.di.module

import com.olivia.plant.ui.image_detection.camera.CameraViewModel
import com.olivia.plant.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CameraViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}