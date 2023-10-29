package com.olivia.plant.ui.image_detection.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.detection.DataDetection
import com.olivia.plant.databinding.ActivityResultDetectionBinding
import com.olivia.plant.ui.image_detection.result.detail.ResultDetailActivity
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class ResultDetectionActivity : BaseActivity<ActivityResultDetectionBinding>() {
    private val dataDetection: DataDetection by intent("dataDetection")
    private val adapter: ResultDetectionAdapter by lazy {
        ResultDetectionAdapter {
            startActivity(ResultDetailActivity::class.java) { intent ->
                intent.putExtra("dataLeafsDisease", it)
            }
        }
    }

    override fun initUI() {
        super.initUI()
        with(binding) {
            rvData.also {
                it.layoutManager = LinearLayoutManager(this@ResultDetectionActivity)
                it.adapter = adapter
            }
            btnBack.onClick { finish() }
            adapter.setNewInstance(dataDetection.dataLeafsDisease.toMutableList())
        }
    }

    override fun initData() {
        super.initData()
    }
}