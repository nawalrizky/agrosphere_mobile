package com.olivia.plant.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.db.model.request.history.DataParamHistory
import com.olivia.plant.data.network.ApiEndpoint
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.viewmodel.observeDynamic

class HomeViewModel(private val endpoint: ApiEndpoint) : ViewModel() {
    val stateHistory: MutableLiveData<DevStateDynamic> by liveData()

    fun getHistory(
        last_start: Int,
        last_end: Int
    ) {
        endpoint.detectionHistory(
            DataParamHistory(
                last_start,
                last_end
            )
        ).observeDynamic(stateHistory)
    }

}