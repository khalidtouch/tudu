package com.reel.tudu.di.module

import android.app.Application
import androidx.room.Room
import com.reel.tudu.data.TodoDao
import com.reel.tudu.data.TodoDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideTodoDb(): TodoDb =
        TodoDb.instance(application)

    @Singleton
    @Provides
    fun provideTodoDao(db: TodoDb): TodoDao =
        db.todoDao
}