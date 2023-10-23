package com.olivia.plant.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.user.DataUser
import com.olivia.plant.databinding.ActivityRegisterBinding
import com.olivia.plant.root.App
import com.olivia.plant.ui.login.LoginActivity
import com.olivia.plant.ui.main.MainActivity
import com.olivia.plant.utils.validateEditText
import com.oratakashi.viewbinding.core.tools.isNull
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.zerobase.data.viewmodel.observerDynamic
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModel()
    override fun initAction() {
        super.initAction()
        with(binding) {
            btnLogin.setOnClickListener {
                finish()
            }

            btnRegister.onClick {
                if (validateEditText(
                        listOf(
                            etFirstName,
                            etLastName,
                            etEmail,
                            etUsername,
                            etPassword
                        )
                    )
                ) {
                    toast("Data tidak boleh kosong")
                } else {
                    viewModel.postRegister(
                        etFirstName.text.toString(),
                        etLastName.text.toString(),
                        etUsername.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                }
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.stateResgister.observerDynamic<DataUser>(
            this,
            onLoading = {
                spotsDialogWait.show()
            },
            onFailed = {
                spotsDialogWait.dismiss()
            },
            onResultAll = {
                spotsDialogWait.dismiss()
                if (it.data.isNull()) {
                    toast("Register Gagal")
                } else {
                    toast("Berhasil Register")
                    startActivity(LoginActivity::class.java)
                    finish()
                }
            },
            onError = {
                spotsDialogWait.dismiss()
                toast(it.message.toString())
            }
        )
    }
}