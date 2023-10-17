package com.olivia.plant.data.db.session

import android.content.Context
import android.content.SharedPreferences
import com.olivia.plant.data.db.model.user.DataUser

class Sessions(context: Context) {
    companion object {
        const val PREF_NAME = "com.olivia.plant.session"

        const val ID: String = "ID"
        const val FULLNAME: String = "fullname"
        const val USERNAME: String = "username"
        const val EMAIL: String = "email"
    }

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun getPref(): SharedPreferences {
        return pref
    }

    fun isLogin(): Boolean {
        return getData(FULLNAME) != ""
    }

    fun putData(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun putData(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun putData(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getData(key: String, default: String = ""): String {
        return pref.getString(key, default).toString()
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return pref.getBoolean(key, default)
    }

    fun getInt(key: String, default: Int = 0): Int {
        return pref.getInt(key, default)
    }

    fun doLogin(dataUser: DataUser) {
        putData(ID, dataUser.id)
        putData(FULLNAME, dataUser.name)
        putData(USERNAME, dataUser.username)
        putData(EMAIL, dataUser.email)
    }
}