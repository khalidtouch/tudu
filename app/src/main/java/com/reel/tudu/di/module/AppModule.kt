package com.reel.tudu.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reel.tudu.data.repositories.LocalTodoRepository
import com.reel.tudu.data.repositories.TodoRepository
import com.reel.tudu.ui.addnew.AddNewViewModel
import com.reel.tudu.ui.addnew.AddNewViewModelFactory
import com.reel.tudu.ui.home.HomeViewModel
import com.reel.tudu.ui.home.HomeViewModelFactory
import com.reel.tudu.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideTodoRepository(repository: LocalTodoRepository): TodoRepository
}



@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    internal abstract fun homeViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddNewViewModel::class)
    internal abstract fun addNewViewModel(viewModel: AddNewViewModel): ViewModel


    @Binds
    internal abstract fun addNewViewModelFactory(factory: AddNewViewModelFactory): ViewModelProvider.Factory
}

