package com.olivia.plant.ui.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.db.model.request.detect.DataParamDetect
import com.olivia.plant.data.network.ApiEndpoint
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.viewmodel.observeDynamic

class NotificationViewModel(private val endpoint: ApiEndpoint) : ViewModel() {
    val stateNotifikasi: MutableLiveData<DevStateDynamic> by liveData()

    fun getNotification() {
        endpoint.notificationHistory().observeDynamic(stateNotifikasi)
    }

}