package com.olivia.plant.ui.login

import android.util.Log
import com.olivia.plant.data.db.model.response.user.DataUser
import com.olivia.plant.databinding.ActivityLoginBinding
import com.olivia.plant.root.App
import com.olivia.plant.ui.main.MainActivity
import com.olivia.plant.ui.register.RegisterActivity
import com.oratakashi.viewbinding.core.tools.isNotNull
import com.oratakashi.viewbinding.core.tools.isNull
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.zerobase.data.viewmodel.observer
import com.zero.zerobase.data.viewmodel.observerDynamic
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModel()

    override fun initAction() {
        super.initAction()
        with(binding) {
            btnRegister.onClick {
                startActivity(RegisterActivity::class.java)
                finish()
            }

            btnLogin.onClick {
                if (etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()) {
                    toast("Email atau Password tidak boleh kosong")
                } else {
                    viewModel.postLogin(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                }
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.stateLogin.observerDynamic<DataUser>(
            this,
            onLoading = {
                spotsDialogWait.show()
            },
            onFailed = {
                spotsDialogWait.dismiss()
            },
            onEmpty = {
                toast("On Empty")
            },
            onResultAll = {
                spotsDialogWait.dismiss()
                if (it.data.isNull()) {
                    toast("Email atau Password salah")
                } else {
                    App.sessions.doLogin(it.data!!)
                    startActivity(MainActivity::class.java)
                    finish()
                }
            },
            onResult = {
                spotsDialogWait.dismiss()
                App.sessions.doLogin(it)
                startActivity(MainActivity::class.java)
                finish()
            },
            onError = {
                spotsDialogWait.dismiss()
                Log.e("TAG", "initObserver: ${it.message}}")
                if (it.message.toString() == "HTTP 401 Unauthorized") {
                    toast("Email atau Password salah")
                } else {
                    toast(it.message.toString())
                }
            }
        )
    }
}