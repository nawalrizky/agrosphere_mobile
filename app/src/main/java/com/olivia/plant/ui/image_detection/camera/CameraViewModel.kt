package com.olivia.plant.ui.image_detection.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.network.ApiEndpoint
import com.olivia.plant.data.network.state.SimpleState
import com.oratakashi.viewbinding.core.binding.livedata.liveData

class CameraViewModel(private val endpoint: ApiEndpoint) : ViewModel() {
    val stateMain: MutableLiveData<SimpleState> by liveData()

    fun requestToken(auth: String) {

    }

}