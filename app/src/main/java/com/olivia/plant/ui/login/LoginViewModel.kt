package com.olivia.plant.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.db.model.request.login.DataParamLogin
import com.olivia.plant.data.network.ApiEndpoint
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.zerobase.data.state.DevState
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.viewmodel.observe
import com.zero.zerobase.data.viewmodel.observeDynamic

class LoginViewModel(
    private val endpoint: ApiEndpoint
) : ViewModel() {

    val stateLogin: MutableLiveData<DevStateDynamic> by liveData()

    fun postLogin(username: String, password: String) {
        endpoint.login(DataParamLogin(username, password)).observeDynamic(stateLogin)
    }
}