package com.olivia.plant.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivityRegisterBinding
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override fun initAction() {
        super.initAction()
        with(binding) {
            btnLogin.setOnClickListener {
                finish()
            }
        }
    }
}