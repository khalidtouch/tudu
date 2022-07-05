package com.reel.tudu

import android.app.Application
import com.reel.tudu.di.component.ApplicationComponent
import com.reel.tudu.di.component.DaggerApplicationComponent


class InitApp : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
    }
}