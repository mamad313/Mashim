package com.example.back4appshop

import com.parse.Parse;
import android.app.Application;


class Back4App : Application() {
    override fun onCreate() {
        super.onCreate()

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("xMlcvYenxOKi3LBnh3GXMMVy16dOOtkLF9IvkM9L")
                .clientKey("kethlAawGdkYq8ciiPjMOYAXYXNSkcOiT9lLIAPv")
                .server("https://parseapi.back4app.com")
                .build()
        )
    }
}