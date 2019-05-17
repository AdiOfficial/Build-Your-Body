package com.anaumchik.buildyourbody.data.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.anaumchik.buildyourbody.data.utils.PlayerSharedPrefs.Companion.PREF_NAME


abstract class SharedPrefs(application: Application) {
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun put(key: String, value: String = "") = sharedPreferences.edit().putString(key, value).apply()
    fun put(key: String, value: Long = 0L) = sharedPreferences.edit().putLong(key, value).apply()
    fun put(key: String, value: Int = 0) = sharedPreferences.edit().putInt(key, value).apply()
    fun put(key: String, value: Float = 0F) = sharedPreferences.edit().putFloat(key, value).apply()
    fun put(key: String, value: Boolean = false) = sharedPreferences.edit().putBoolean(key, value).apply()

    fun getString(key: String, default: String = ""): String = sharedPreferences.getString(key, default) ?: ""
    fun getLong(key: String, default: Long = 0L): Long = sharedPreferences.getLong(key, default)
    fun getInt(key: String, default: Int = 0): Int = sharedPreferences.getInt(key, default)
    fun getFloat(key: String, default: Float = 0F): Float = sharedPreferences.getFloat(key, default)
    fun getBoolean(key: String, default: Boolean = false): Boolean = sharedPreferences.getBoolean(key, default)

    fun delete(key: String) = sharedPreferences.edit().remove(key).apply()
}
