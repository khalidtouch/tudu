package com.reel.tudu.data

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.reel.tudu.entities.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [TodoItem::class],
    version = 2
)
abstract class TodoDb : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object {
        val TAG = "TodoDb"

        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d(TAG, "onCreate: DB has been created")
                CoroutineScope(Dispatchers.IO).launch {
                    initData()
                }
            }
        }

        @Volatile
        private var INSTANCE: TodoDb? = null

        fun instance(application: Application): TodoDb {
            synchronized(this) {}
            return INSTANCE ?: Room.databaseBuilder(
                application,
                TodoDb::class.java,
                "todo_db"
            )
                .fallbackToDestructiveMigration()
                .addCallback(callback)
                .build().also {
                    INSTANCE = it
                }
        }

        private suspend fun initData() {
            val todoItems = MockDb.todoItems
            val dao = this.INSTANCE?.todoDao
            dao?.saveTodoItem(todoItems[0])
            dao?.saveTodoItem(todoItems[1])
            dao?.saveTodoItem(todoItems[2])
            dao?.saveTodoItem(todoItems[3])
        }
    }


}

