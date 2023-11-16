package com.olivia.plant.ui.profile

import com.olivia.plant.data.db.session.Sessions
import com.olivia.plant.databinding.FragmentProfileBinding
import com.olivia.plant.root.App
import com.olivia.plant.ui.login.LoginActivity
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.zero.zerobase.presentation.viewbinding.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initUI() {
        super.initUI()
        with(binding){
            tvUsername.text = App.sessions.getData(Sessions.USERNAME)
            tvEmail.text = App.sessions.getData(Sessions.EMAIL)

            lLogout.onClick {
                App.sessions.doLogout()
                startActivity(LoginActivity::class.java)
                requireActivity().finish()
            }
        }
    }

}