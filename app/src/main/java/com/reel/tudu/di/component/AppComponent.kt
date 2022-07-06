package com.reel.tudu.di.component

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.reel.tudu.MainActivity
import com.reel.tudu.di.RepositoryModule
import com.reel.tudu.di.ViewModelModule
import com.reel.tudu.di.module.RoomModule

import com.reel.tudu.ui.addnew.AddNewViewModel
import com.reel.tudu.ui.addnew.AddTodoBottomSheet
import com.reel.tudu.ui.completed.CompletionFragment
import com.reel.tudu.ui.home.HomeFragment
import com.reel.tudu.ui.home.HomeFragmentComponent
import com.reel.tudu.ui.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class, RoomModule::class])
interface ApplicationComponent {


    fun inject(frag: CompletionFragment)

    fun inject(activity: MainActivity)

    fun inject(frag: SettingsFragment)

    fun homeFragmentComponent(): HomeFragmentComponent.Factory

}