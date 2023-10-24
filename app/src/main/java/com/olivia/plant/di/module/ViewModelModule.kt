package com.olivia.plant.di.module

import com.olivia.plant.ui.image_detection.camera.CameraViewModel
import com.olivia.plant.ui.login.LoginViewModel
import com.olivia.plant.ui.monitoring.MonitoringViewModel
import com.olivia.plant.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CameraViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MonitoringViewModel(get(named("service_esp"))) }
}