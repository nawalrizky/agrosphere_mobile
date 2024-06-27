package com.olivia.plant.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.history.DataDetectionHistoryItem
import com.olivia.plant.databinding.ActivityHistoryBinding
import com.olivia.plant.databinding.LayoutDataLoadingBinding
import com.olivia.plant.databinding.LayoutDataNullBinding
import com.olivia.plant.ui.history.detail.HistoryDetailActivity
import com.olivia.plant.ui.home.HistoryAdapter
import com.olivia.plant.ui.home.HomeViewModel
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.zerobase.data.viewmodel.observerDynamic

import com.zero.zerobase.presentation.viewbinding.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<ActivityHistoryBinding>() {

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
            btnBack.setOnClickListener { finish() }
            rvData.layoutManager = LinearLayoutManager(this@HistoryActivity)
            rvData.adapter = adapter

            srHistory.setOnRefreshListener {
                viewModel.getHistory(0, -1)
                srHistory.isRefreshing = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHistory(0, -1)
    }

    override fun initObserver() {
        super.initObserver()

        viewModel.stateHistory.observerDynamic<List<DataDetectionHistoryItem>>(
            this,
            onLoading = {
                adapter.setNewInstance(mutableListOf())
                adapter.setEmptyView(LayoutDataLoadingBinding.inflate(layoutInflater).root)
            },
            onResultAll = {
                adapter.setNewInstance(it.data?.toMutableList())
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
}
