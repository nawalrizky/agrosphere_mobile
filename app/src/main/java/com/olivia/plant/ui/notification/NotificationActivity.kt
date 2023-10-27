package com.olivia.plant.ui.notification

import androidx.recyclerview.widget.LinearLayoutManager
import com.olivia.plant.data.db.model.response.notification.DataNotification
import com.olivia.plant.databinding.ActivityNotificationBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.zerobase.data.viewmodel.observerDynamic
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationActivity : BaseActivity<ActivityNotificationBinding>() {

    val adapter: NotificationAdapter by lazy {
        NotificationAdapter{

        }
    }

    private val viewModel: NotificationViewModel by viewModel()

    override fun initUI() {
        super.initUI()
        with(binding) {
            rvData.also {
                it.layoutManager = LinearLayoutManager(this@NotificationActivity)
                it.adapter = adapter
            }
        }

        viewModel.getNotification()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.stateNotifikasi.observerDynamic<List<DataNotification>>(
            this,
            onLoading = {
                spotsDialogWait.show()
            },
            onResultAll = {
                adapter.setNewInstance(it.data?.toMutableList())
                spotsDialogWait.dismiss()
            },
            onFailed = {
                toast(it)
                spotsDialogWait.dismiss()
            },
            onError = {
                spotsDialogWait.dismiss()
                toast(it.message.toString() )
            }
        )
    }
}