package com.olivia.plant.ui.image_detection.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivityResultNotDetectedBinding
import com.oratakashi.viewbinding.core.tools.onClick

class ResultNotDetectedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultNotDetectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultNotDetectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.onClick { finish() }
    }
}