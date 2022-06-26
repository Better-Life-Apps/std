package com.betterlifeapps.std.components.settings

import android.content.Context
import android.content.SharedPreferences

interface Settings {
    fun getInt(key: String, defValue: Int): Int
    fun setInt(key: String, value: Int)
    fun getBool(key: String, defValue: Boolean): Boolean
    fun setBool(key: String, value: Boolean)
    fun getLong(key: String, defValue: Long): Long
    fun setLong(key: String, value: Long)

    companion object {
        const val KEY_LAUNCH_COUNT = "launch_count"
        const val KEY_RATING_SUBMITTED = "rating_submitted"
    }
}

class SettingsImpl(
    val context: Context
) : Settings {

    private val prefsName = context.packageName + "_PREFS"

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    override fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    override fun setInt(key: String, value: Int) {
        sharedPreferences.edit()
            .putInt(key, value)
            .apply()
    }

    override fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    override fun setLong(key: String, value: Long) {
        sharedPreferences.edit()
            .putLong(key, value)
            .apply()
    }

    override fun getBool(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun setBool(key: String, value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(key, value)
            .apply()
    }
}