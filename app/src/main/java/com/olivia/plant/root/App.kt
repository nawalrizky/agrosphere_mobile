package com.olivia.plant.root

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.ApplicationInfo
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.olivia.plant.data.db.session.Sessions
import com.olivia.plant.di.module.networkModule
import com.olivia.plant.di.module.viewModelModule
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


