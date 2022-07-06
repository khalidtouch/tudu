package com.reel.tudu.data.repositories

import com.reel.tudu.entities.TodoItem

interface TodoRepository {
    suspend fun getAllTodoItems(): List<TodoItem>

    suspend fun getAllCompletedTasks(): List<TodoItem>


    suspend fun getTodoItemById(itemId: Int): TodoItem

    suspend fun saveTodoItem(todoItem: TodoItem)
}

