package com.coala.play_app

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name",Context.MODE_PRIVATE)

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        val value : Boolean = prefs.getBoolean(key,false)
        return value
    }

    fun setBoolean(key: String, context: Context, value: Boolean) {
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(key,value)
        editor.commit()
    }
}