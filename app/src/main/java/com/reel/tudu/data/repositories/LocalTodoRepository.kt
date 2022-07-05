package com.reel.tudu.data.repositories

import com.reel.tudu.data.MockDb
import com.reel.tudu.data.TodoDao
import com.reel.tudu.entities.CompletedTask
import com.reel.tudu.entities.TodoItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalTodoRepository
    @Inject constructor(private val todoDao: TodoDao) : TodoRepository {

    override suspend fun getAllTodoItems(): List<TodoItem> {
        return todoDao.getAllTodoItems()
    }

    override suspend fun getAllCompletedTasks(): List<CompletedTask> {
        return todoDao.getAllCompletedTasks()
    }

    override suspend fun getCompletedTaskByDay(day: Int): CompletedTask {
        return todoDao.getCompletedTaskByDay(day)
    }

    override suspend fun getTodoItemById(itemId: Int): TodoItem {
        return todoDao.getTodoItemById(itemId)
    }

    override suspend fun saveTodoItem(todoItem: TodoItem) {
        todoDao.saveTodoItem(todoItem)
    }

    override suspend fun saveCompletedTask(completedTask: CompletedTask) {
        todoDao.saveCompletedTask(completedTask)
    }
}