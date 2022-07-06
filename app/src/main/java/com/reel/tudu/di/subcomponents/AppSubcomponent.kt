package com.reel.tudu.di.subcomponents

import com.reel.tudu.ui.home.HomeFragmentComponent
import dagger.Module


@Module(
    subcomponents = [
        HomeFragmentComponent::class
    ]
)
class AppSubcomponent {
}