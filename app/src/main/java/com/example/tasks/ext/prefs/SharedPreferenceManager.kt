package com.example.tasks.ext.prefs

import android.content.Context
import com.google.gson.Gson
import com.securepreferences.SecurePreferences
import com.example.tasks.ext.constant.ConstKeys
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(context: Context) {

    private var securePreferences: SecurePreferences =
        SecurePreferences(context, ConstKeys.SALT_KEY, ConstKeys.PREFERENCE_NAME)
    private var gson = Gson()

    companion object {
        private const val PREFS_KEY_USER_LOGIN_PLATFORM = "USER_LOGIN_PLATFORM"
    }

    fun clearPreferences() {
        securePreferences.edit().clear().commit()
    }

    var userLoginPlatform: String
        get(): String {
            return securePreferences.getString(PREFS_KEY_USER_LOGIN_PLATFORM, "").toString()
        }
        set(value) {
            securePreferences.edit().putString(PREFS_KEY_USER_LOGIN_PLATFORM, value).apply()
        }

    fun getString(key: String): String {
        return securePreferences.getString(key, "").toString()
    }

    fun setString(key: String, value: String) {
        securePreferences.edit().putString(key, value).apply()
    }

    fun getInt(key: String): Int {
        return securePreferences.getInt(key, 0)
    }

    fun setInt(key: String, value: Int) {
        securePreferences.edit().putInt(key, value).apply()
    }

}