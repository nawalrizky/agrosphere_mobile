package com.olivia.plant.ui.monitoring

import android.annotation.SuppressLint
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebViewClient
import com.olivia.plant.R
import com.olivia.plant.data.network.EspEndpoint
import com.olivia.plant.databinding.ActivityMonitoringBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonitoringActivity : BaseActivity<ActivityMonitoringBinding>() {

    private val viewModel: MonitoringViewModel by viewModel()
    private var isFlashOn = false
    override fun initUI() {
        super.initUI()
        with(binding) {
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.settings.useWideViewPort = true
            webView.settings.loadWithOverviewMode = true
            webView.loadUrl("http://192.168.6.232:81/stream")
        }

    }

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun initAction() {
        super.initAction()

        with(binding) {
            btnUp.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        viewModel.postAction(EspEndpoint.ESPACTION.FORWARD)
                    }

                    MotionEvent.ACTION_UP -> {
                        viewModel.postAction(EspEndpoint.ESPACTION.STOP)
                    }
                }

                v?.onTouchEvent(event) ?: true
            }


            btnDown.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        viewModel.postAction(EspEndpoint.ESPACTION.BACKWARD)
                    }

                    MotionEvent.ACTION_UP -> {
                        viewModel.postAction(EspEndpoint.ESPACTION.STOP)
                    }
                }
                v?.onTouchEvent(event) ?: true
            }

            btnFlash.onClick {
                isFlashOn = if (isFlashOn) {
                    viewModel.postAction(EspEndpoint.ESPACTION.OFF_LAMP)
                    imageFlash.setImageDrawable(resources.getDrawable(R.drawable.ic_flashlight_off))
                    imageFlash.setColorFilter(resources.getColor(R.color.black))
                    false
                } else {
                    viewModel.postAction(EspEndpoint.ESPACTION.ON_LAMP)
                    imageFlash.setImageDrawable(resources.getDrawable(R.drawable.ic_flashlight))
                    imageFlash.setColorFilter(resources.getColor(R.color.black))
                    true
                }
            }

            btnScan.onClick {
                viewModel.postAction(EspEndpoint.ESPACTION.DETECT)
            }

            btnBack.onClick {
                finish()
            }

        }


    }
}