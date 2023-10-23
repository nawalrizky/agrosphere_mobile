package com.olivia.plant.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivitySplashBinding
import com.olivia.plant.root.App
import com.olivia.plant.ui.login.LoginActivity
import com.olivia.plant.ui.main.MainActivity
import com.oratakashi.viewbinding.core.tools.startActivity
import com.zero.zerobase.presentation.viewbinding.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun initAction() {
        super.initAction()

        Handler().postDelayed({
            if (App.sessions.isLogin()) {
                startActivity(MainActivity::class.java)
            } else {
                startActivity(LoginActivity::class.java)
            }
            finish()
        }, 2000L)
    }
}