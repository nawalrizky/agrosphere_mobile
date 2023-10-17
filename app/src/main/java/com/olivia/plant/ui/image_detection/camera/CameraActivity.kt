package com.olivia.plant.ui.image_detection.camera

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivityCameraBinding
import com.olivia.plant.utils.byteArrayToBitmap
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.oratakashi.viewbinding.core.tools.visible
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction
import com.otaliastudios.cameraview.size.Size
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class CameraActivity : BaseActivity<ActivityCameraBinding>() {

    private var isPreview = false
    override fun initUI() {
        super.initUI()
        with(binding) {
            camera.setLifecycleOwner(this@CameraActivity)
        }
    }

    private fun initCameraConf() {
        with(binding) {
            camera.also {
                it.mode = Mode.PICTURE
                it.flash = Flash.OFF
                it.playSounds = false
                it.keepScreenOn = true
                it.pictureMetering = false
                it.useDeviceOrientation = false
                it.mapGesture(Gesture.PINCH, GestureAction.ZOOM)
                it.mapGesture(Gesture.TAP, GestureAction.AUTO_FOCUS)
            }
        }
    }

    override fun initAction() {
        super.initAction()
        with(binding) {
            initCameraConf()
            btnCapture.setOnClickListener {
                camera.takePicture()
            }

            btnBack.onClick {
                onBackClick()
            }

            btnFlash.onClick {
                when (camera.flash) {
                    Flash.OFF -> {
                        camera.flash = Flash.ON
                        btnFlash.setImageResource(R.drawable.ic_flash_on)
                    }

                    Flash.ON -> {
                        camera.flash = Flash.AUTO
                        btnFlash.setImageResource(R.drawable.ic_flash_auto)
                    }

                    Flash.AUTO -> {
                        camera.flash = Flash.OFF
                        btnFlash.setImageResource(R.drawable.ic_flash_off)
                    }

                    else -> {
                        camera.flash = Flash.OFF
                        btnFlash.setImageResource(R.drawable.ic_flash_off)
                    }
                }
            }
        }
    }

    override fun initObserver() {
        super.initObserver()

        binding.camera.addCameraListener(
            object : com.otaliastudios.cameraview.CameraListener() {

                override fun onPictureShutter() {
                    super.onPictureShutter()
                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                    result.toBitmap(
                        binding.imgPreview.width,
                        binding.imgPreview.height
                    ) { bitmap -> binding.imgPreview.setImageBitmap(bitmap) }
                    binding.imgPreview.visible()
                    binding.camera.gone()
                    binding.btnCapture.setImageDrawable(getDrawable(R.drawable.ic_done_capture))
                    isPreview = true
                }
            }
        )

        // Back pressed
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackClick()
            }
        })

    }

    private fun onBackClick() {
        if (isPreview) {
            binding.imgPreview.gone()
            binding.imgPreview.setImageBitmap(null)
            binding.btnCapture.setImageDrawable(getDrawable(R.drawable.ic_button_camera))
            binding.camera.visible()
            isPreview = false
        } else {
            finish()
        }
    }

}