package com.reel.tudu.data.repositories

import com.reel.tudu.data.MockDb
import com.reel.tudu.entities.TodoItem

class TodoRepository {
    suspend fun getAllTodoItems(): List<TodoItem> {
        return MockDb.todoItems
    }
}