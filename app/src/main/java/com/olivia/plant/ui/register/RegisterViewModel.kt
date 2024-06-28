package com.olivia.plant.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.db.model.request.register.DataParamRegister
import com.olivia.plant.data.network.ApiEndpoint
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.zerobase.data.state.DevState
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.viewmodel.observe
import com.zero.zerobase.data.viewmodel.observeDynamic

class RegisterViewModel(
    private val endpoint: ApiEndpoint
) : ViewModel() {

    val stateResgister: MutableLiveData<DevStateDynamic> by liveData()

    fun postRegister(
        firstname: String,
        lastname: String,
        email: String,
        username: String,
        password: String,
    ) {
        endpoint.register(
            DataParamRegister(
                firstname,
                lastname,
                email,
                username,
                password
            )
        ).observeDynamic(stateResgister)
    }
}