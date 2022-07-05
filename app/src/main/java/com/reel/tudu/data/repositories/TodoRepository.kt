package com.reel.tudu.data.repositories

import android.content.Context
import com.reel.tudu.data.MockDb
import com.reel.tudu.data.TodoDao
import com.reel.tudu.data.TodoDb
import com.reel.tudu.entities.CompletedTask
import com.reel.tudu.entities.TodoItem

interface TodoRepository {
    suspend fun getAllTodoItems(): List<TodoItem>

    suspend fun getAllCompletedTasks(): List<CompletedTask>

    suspend fun getCompletedTaskByDay(day: Int): CompletedTask

    suspend fun getTodoItemById(itemId: Int): TodoItem

    suspend fun saveTodoItem(todoItem: TodoItem)

    suspend fun saveCompletedTask(completedTask: CompletedTask)
}

