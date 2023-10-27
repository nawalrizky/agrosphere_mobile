package com.olivia.plant.root

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.messaging.FirebaseMessaging
import com.olivia.plant.data.db.session.Sessions
import com.olivia.plant.di.module.networkModule
import com.olivia.plant.di.module.viewModelModule
import com.zero.zerobase.presentation.toast.showShortToast
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


@SuppressLint("StaticFieldLeak")
class App : Application() {
    companion object {
        lateinit var sessions: Sessions
    }

    override fun onCreate() {
        super.onCreate()
        sessions = Sessions(this)
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    viewModelModule
                )
            )
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        createNotificationChannel()
        if (sessions.isLogin()) {
            subscribeTopic()
        }
        // Subscribe to a topic
    }

    private fun subscribeTopic() {
        val idUser = sessions.getInt(Sessions.ID).toString()
        FirebaseMessaging.getInstance().subscribeToTopic("detection_${idUser}")
        Log.e("TAG", "subscribeTopic: detection_${idUser}")
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Plant Notification Channel",
                "Plant Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(channel)
        }
    }

}


