package com.example.himanshactivitiessample.utils

import android.content.Context
import android.content.SharedPreferences

//A SharedPreferences object points to a file containing key-value pairs and provides simple methods to read and write them

//apply() changes the in-memory SharedPreferences object immediately but writes the updates to disk asynchronously.
// Alternatively, you can use commit() to write the data to disk synchronously.


class PrefHelper (context: Context) {

    private val PREFS_NAME = "sharedpref12345"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }


    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }

}