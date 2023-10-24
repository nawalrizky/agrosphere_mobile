package com.olivia.plant.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olivia.plant.R
import com.olivia.plant.databinding.FragmentHomeBinding
import com.olivia.plant.ui.monitoring.MonitoringActivity
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.zero.zerobase.presentation.viewbinding.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initUI() {
        super.initUI()
        with(binding) {
            btnMonitoring.onClick {
                startActivity(MonitoringActivity::class.java)
            }
        }
    }

}