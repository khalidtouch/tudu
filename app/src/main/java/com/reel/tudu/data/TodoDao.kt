package com.reel.tudu.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reel.tudu.entities.CompletedTask
import com.reel.tudu.entities.TodoItem

@Dao
interface TodoDao {
    //POST
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodoItem(todoItem: TodoItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCompletedTask(completedTask: CompletedTask)

    //GET
    @Query("SELECT * FROM todoItem WHERE itemId = :itemId")
    suspend fun getTodoItemById(itemId: Int): TodoItem

    @Query("SELECT * FROM todoItem")
    suspend fun getAllTodoItems(): List<TodoItem>

    @Query("SELECT * FROM completedTask WHERE completionDay = :day")
    suspend fun getCompletedTaskByDay(day: Int): CompletedTask

    @Query("SELECT * FROM completedTask")
    suspend fun getAllCompletedTasks(): List<CompletedTask>

//DELETE
    //todo
}