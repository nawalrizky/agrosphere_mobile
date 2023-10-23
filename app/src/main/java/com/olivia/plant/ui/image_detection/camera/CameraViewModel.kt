package com.olivia.plant.ui.image_detection.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.db.model.request.detect.DataParamDetect
import com.olivia.plant.data.network.ApiEndpoint
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.viewmodel.observeDynamic

class CameraViewModel(private val endpoint: ApiEndpoint) : ViewModel() {
    val statePostImage: MutableLiveData<DevStateDynamic> by liveData()

    fun postImage(
        plant_img: String,
    ) {
        endpoint.detectLeaf(
            DataParamDetect(
                plant_img
            )
        ).observeDynamic(statePostImage)
    }

}