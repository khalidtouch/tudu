package com.reel.tudu.ui.home

import com.reel.tudu.ui.addnew.AddTodoBottomSheet
import com.reel.tudu.utils.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeFragmentComponent
    }

    fun inject(fragment: AddTodoBottomSheet)
    fun inject(fragment: HomeFragment)
}