package com.example.workapplication.utils

import android.content.Context.MODE_PRIVATE
import com.example.workapplication.MainApplication

object PreferenceManager {

    private val sharePref by lazy {
        MainApplication.applicationContext().getSharedPreferences("work-app", MODE_PRIVATE)
    }

    var username: String?
        get() = sharePref.getString("username", null)
        set(value) {
            sharePref.edit().putString("username", value).apply()
        }

    var objectId: String?
        get() = sharePref.getString("objectId", null)
        set(value) {
            sharePref.edit().putString("objectId", value).apply()
        }

    var token:String?
        get() = sharePref.getString("token", null)
        set(value) {
            sharePref.edit().putString("token", value).apply()
        }

}