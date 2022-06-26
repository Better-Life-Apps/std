package com.betterlifeapps.std.components.settings

import android.content.Context

object SettingsHolder {

    private var settings: Settings? = null

    fun get(context: Context): Settings {
        synchronized(this) {
            if (settings == null) {
                settings = SettingsImpl(context)
            }
            return settings!!
        }
    }
}