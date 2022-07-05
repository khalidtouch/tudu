package com.reel.tudu.di.component

import android.content.Context
import com.reel.tudu.MainActivity
import com.reel.tudu.di.RepositoryModule
import com.reel.tudu.di.ViewModelModule
import com.reel.tudu.ui.completed.CompletionFragment
import com.reel.tudu.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(frag: HomeFragment)

    fun inject(frag: CompletionFragment)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}