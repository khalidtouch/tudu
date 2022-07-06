package com.reel.tudu.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reel.tudu.entities.TodoItem

@Dao
interface TodoDao {
    //POST
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodoItem(todoItem: TodoItem)

    //GET
    @Query("SELECT * FROM todoItem WHERE itemId = :itemId")
    suspend fun getTodoItemById(itemId: Int): TodoItem

    @Query("SELECT * FROM todoItem WHERE isCompleted = 0")
    suspend fun getAllTodoItems(): List<TodoItem>


    @Query("SELECT * FROM todoItem WHERE isCompleted = 1")
    suspend fun getAllCompletedTasks(): List<TodoItem>

//DELETE
    //todo
}