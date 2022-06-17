package com.reel.tudu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reel.tudu.entities.CompletedTask
import com.reel.tudu.entities.TodoItem

@Database(
    entities = [TodoItem::class, CompletedTask::class],
    version = 1
)
abstract class TodoDb : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDb? = null

        fun instance(context: Context): TodoDb {
            synchronized(this) {}
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                TodoDb::class.java,
                "todo_db"
            ).build().also {
                INSTANCE = it
            }
        }
    }
}