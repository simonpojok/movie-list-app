package com.engie.eea_tech_interview

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class BaseApplication: Application() {

//    override fun onCreate() {
//        super.onCreate()
//
//        startKoin {
//            androidContext(this@BaseApplication)
//            modules(getDependencyModules())
//        }
//    }
//
//    open fun getDependencyModules(): List<Module> {
//        return listOf(
//            networkModule
//        )
//    }
}
