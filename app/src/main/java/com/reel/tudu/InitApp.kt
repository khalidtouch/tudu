package com.reel.tudu

import android.app.Application
import com.reel.tudu.di.component.ApplicationComponent
import com.reel.tudu.di.component.DaggerApplicationComponent
import com.reel.tudu.di.module.RoomModule


class InitApp : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .roomModule(RoomModule(this))
            .build()
    }

}