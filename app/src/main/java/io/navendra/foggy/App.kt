package io.navendra.foggy

import android.app.Application

class App : Application(){
    val engine by lazy { FoggyEngine(applicationContext).gecko }
}