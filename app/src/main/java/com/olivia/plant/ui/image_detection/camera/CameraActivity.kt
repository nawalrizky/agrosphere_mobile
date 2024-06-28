package com.olivia.plant.ui.image_detection.camera

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.detection.DataDetection
import com.olivia.plant.data.db.model.response.user.DataUser
import com.olivia.plant.databinding.ActivityCameraBinding
import com.olivia.plant.root.App
import com.olivia.plant.ui.image_detection.result.ResultDetectionActivity
import com.olivia.plant.ui.image_detection.result.ResultNotDetectedActivity
import com.olivia.plant.ui.main.MainActivity
import com.olivia.plant.utils.bitmapToBase64
import com.olivia.plant.utils.bitmapToBase64Async
import com.olivia.plant.utils.byteArrayToBitmap
import com.olivia.plant.utils.compressBitmap
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.isNull
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.oratakashi.viewbinding.core.tools.visible
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction
import com.otaliastudios.cameraview.size.Size
import com.zero.zerobase.data.viewmodel.observerDynamic
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.shouheng.compress.Compress
import me.shouheng.compress.automatic
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class CameraActivity : BaseActivity<ActivityCameraBinding>() {

    private var isPreview = false
    private var resultBitmap: Bitmap? = null

    private val viewModel: CameraViewModel by viewModel()
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
                onCaptureClick()
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

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                    result.toBitmap(
                        binding.imgPreview.width,
                        binding.imgPreview.height
                    ) { bitmap ->
                        autoCompressAndGetBitmapWithBlocking(bitmap!!) {
                            binding.imgPreview.setImageBitmap(it)
                            resultBitmap = it
                        }
                    }
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

        viewModel.statePostImage.observerDynamic<DataDetection>(
            this,
            onLoading = {
                spotsDialogWait.show()
            },
            onFailed = {
                spotsDialogWait.dismiss()
                toast(it)
                onBackClick()
            },
            onEmpty = {
                toast("On Empty")
            },
            onResultAll = {
                spotsDialogWait.dismiss()

                toast(it.message.toString())
                if (it.data?.dataLeafsDisease.isNullOrEmpty()) {
                    startActivity(ResultNotDetectedActivity::class.java)
                } else {
                    startActivity(ResultDetectionActivity::class.java) { intent ->
                        intent.putExtra("dataDetection", it.data)
                    }
                }
                finish()
            },
            onError = {
                spotsDialogWait.dismiss()
                if (it.message.toString().contains("404")) {
                    toast("Penyakit tidak ditemukan")
                } else {
                    toast(it.message.toString())
                }
            }
        )

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun onCaptureClick() {
        if (isPreview) {
            resultBitmap?.let {
                GlobalScope.launch(Dispatchers.Main) {
                    viewModel.postImage(
                        bitmapToBase64Async(it, 100, spotsDialogWait)
                    )
                    // Now, 'base64String' contains the Base64-encoded string
                }

            }
        } else {
            binding.camera.takePicture()
        }
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

    /**
     * Sample:
     * - Source:    [Bitmap]
     * - Algorithm: [automatic]
     * - Launch:    Blocking
     * - Result:    [Bitmap]
     */
    @SuppressLint("CheckResult")
    private fun autoCompressAndGetBitmapWithBlocking(
        srcBitmap: Bitmap,
        onResult: (Bitmap) -> Unit
    ) {
        Observable.create<Bitmap> {
            val out = ByteArrayOutputStream()
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            val byteArray = out.toByteArray()
            val bitmapCompressed = Compress.with(this, byteArray)
                .automatic {
                }
                .asBitmap()
                .get()!!
            it.onNext(bitmapCompressed)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            onResult(it)
        }, {
            toast("error : $it")
        })
    }

}