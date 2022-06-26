package com.betterlifeapps.std

import android.app.Application
import com.betterlifeapps.std.components.settings.Settings
import com.betterlifeapps.std.components.settings.SettingsHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Main).launch {
            val settings = SettingsHolder.get(this@BaseApplication)
            val launchCount = settings.getInt(Settings.KEY_LAUNCH_COUNT, 0)
            settings.setInt(Settings.KEY_LAUNCH_COUNT, launchCount + 1)
        }
    }
}