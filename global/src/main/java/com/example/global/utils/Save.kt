package com.example.global.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

object Save {
    private lateinit var sharedPreferences :SharedPreferences
    private lateinit var editor: Editor

    fun data(context: Context , key:String,value:String){
        sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun fetch(context: Context,key: String): String? {
        sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"")
    }
}