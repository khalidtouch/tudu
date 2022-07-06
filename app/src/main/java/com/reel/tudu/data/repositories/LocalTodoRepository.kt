package com.reel.tudu.data.repositories

import com.reel.tudu.data.MockDb
import com.reel.tudu.data.TodoDao
import com.reel.tudu.entities.TodoItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalTodoRepository
    @Inject constructor(private val todoDao: TodoDao) : TodoRepository {

    override suspend fun getAllTodoItems(): List<TodoItem> {
        return todoDao.getAllTodoItems()
    }

    override suspend fun getAllCompletedTasks(): List<TodoItem> {
        return todoDao.getAllCompletedTasks()
    }


    override suspend fun getTodoItemById(itemId: Int): TodoItem {
        return todoDao.getTodoItemById(itemId)
    }

    override suspend fun saveTodoItem(todoItem: TodoItem) {
        todoDao.saveTodoItem(todoItem)
    }
}