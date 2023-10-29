package com.olivia.plant.ui.before_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivityBeforeLoginBinding
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class BeforeLoginActivity : BaseActivity<ActivityBeforeLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_before_login)
    }
}