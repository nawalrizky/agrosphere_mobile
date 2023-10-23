package com.olivia.plant.ui.image_detection.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.detection.DataDetection
import com.olivia.plant.databinding.ActivityResultDetectionBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class ResultDetectionActivity : BaseActivity<ActivityResultDetectionBinding>() {
    private val dataDetection : DataDetection by intent("dataDetection")

    override fun initUI() {
        super.initUI()


    }
}