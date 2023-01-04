package com.example.back4appshop.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.back4appshop.activities.LoginActivity

public class Setting(private var context: Context) {
    var mypref: SharedPreferences =context.getSharedPreferences("myApp", Activity.MODE_PRIVATE)

    public fun saveLogin(userId:String, username:String){
        val editor: SharedPreferences.Editor =  mypref.edit()
        editor.putString("userId", userId)
        editor.putString("username", username)
        editor.apply()
    }

    public fun setLoginInfo(): String? {
        return mypref.getString("userId",null)
    }

}