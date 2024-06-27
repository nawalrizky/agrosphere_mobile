package com.olivia.plant.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.olivia.plant.data.db.model.response.history.DataDetectionHistoryItem
import com.olivia.plant.data.db.session.Sessions
import com.olivia.plant.databinding.FragmentHomeBinding
import com.olivia.plant.databinding.LayoutDataLoadingBinding
import com.olivia.plant.databinding.LayoutDataNullBinding
import com.olivia.plant.root.App
import com.olivia.plant.ui.augmented_reality.AugmentedRealityActivity
import com.olivia.plant.ui.augmented_reality.ObjectSelectionActivity
import com.olivia.plant.ui.history.HistoryActivity
import com.olivia.plant.ui.history.detail.HistoryDetailActivity
import com.olivia.plant.ui.monitoring.MonitoringActivity
import com.olivia.plant.ui.notification.NotificationActivity
import com.olivia.plant.utils.isDateToday
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.zerobase.data.viewmodel.observerDynamic
import com.zero.zerobase.presentation.viewbinding.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()

    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter {
            startActivity(HistoryDetailActivity::class.java) { intent ->
                intent.putExtra("dataLeafsDisease", it)
            }
        }
    }

    override fun initUI() {
        super.initUI()
        with(binding) {
            btnMonitoring.onClick {
                startActivity(MonitoringActivity::class.java)
            }
            tvUsername.text = "${App.sessions.getData(Sessions.FIRTSNAME)} ${App.sessions.getData(Sessions.LASTNAME)}"

            btnNotification.onClick {
                startActivity(NotificationActivity::class.java)
            }

            btnAR.onClick {
                startActivityForResult(Intent(requireContext(), ObjectSelectionActivity::class.java), REQUEST_OBJECT_SELECTION)
            }

            rvHistory.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }

            tvLihatSemua.onClick {
                startActivity(HistoryActivity::class.java)
            }


        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.getHistory(0, 4)

        viewModel.stateHistory.observerDynamic<List<DataDetectionHistoryItem>>(
            this,
            onLoading = {
                adapter.setNewInstance(mutableListOf())
                adapter.setEmptyView(LayoutDataLoadingBinding.inflate(layoutInflater).root)
            },
            onResultAll = {
                adapter.setNewInstance(it.data?.toMutableList())
                adapter.setEmptyView(LayoutDataNullBinding.inflate(layoutInflater).root)

                var totalToday = 0
                it.data?.forEach {
                    if (it.createdAt.isDateToday()) {
                        totalToday++
                    }
                }
                binding.tvTotalToday.text = totalToday.toString()
                Unit
            },
            onFailed = {
                toast(it)
                adapter.setEmptyView(LayoutDataNullBinding.inflate(layoutInflater).root)
            },
            onError = {
                toast(it.message.toString())
                adapter.setEmptyView(LayoutDataNullBinding.inflate(layoutInflater).root)
            }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_OBJECT_SELECTION && resultCode == AppCompatActivity.RESULT_OK) {
            val selectedObject = data?.getStringExtra("selectedObject")
            val intent = Intent(requireContext(), AugmentedRealityActivity::class.java).apply {
                putExtra("selectedObject", selectedObject)
            }
            startActivity(intent)
        }
    }

    companion object {
        private const val REQUEST_OBJECT_SELECTION = 1001
    }
}
