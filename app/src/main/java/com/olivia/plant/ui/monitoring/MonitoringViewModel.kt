package com.olivia.plant.ui.monitoring

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olivia.plant.data.network.EspEndpoint
import com.olivia.plant.data.network.state.SimpleState
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.viewmodel.observeDynamic
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MonitoringViewModel(
    private val endpoint: EspEndpoint
) : ViewModel() {

    val stateAction: MutableLiveData<SimpleState> by liveData()

    fun postAction(action: EspEndpoint.ESPACTION) {
        endpoint.action(action.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<SimpleState>(SimpleState::Result)
            .onErrorReturn(SimpleState::Error)
            .toFlowable()
            .startWith(SimpleState.Loading)
            .subscribe(stateAction::postValue)
            .let { return@let CompositeDisposable::add }
    }
}