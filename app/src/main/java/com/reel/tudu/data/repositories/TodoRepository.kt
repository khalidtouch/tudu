package com.reel.tudu.data.repositories

import android.content.Context
import com.reel.tudu.data.MockDb
import com.reel.tudu.data.TodoDao
import com.reel.tudu.data.TodoDb
import com.reel.tudu.entities.CompletedTask
import com.reel.tudu.entities.TodoItem

class TodoRepository() {
   // private val todoDao = TodoDb.instance(context).todoDao
    suspend fun getAllTodoItems(): List<TodoItem> {
      //  return todoDao.getAllTodoItems() //todo
        return MockDb.todoItems
    }
/**
    suspend fun getAllCompletedTasks(): List<CompletedTask> {
        return todoDao.getAllCompletedTasks()
    }

    suspend fun getCompletedTaskByDay(day: Int): CompletedTask {
        return todoDao.getCompletedTaskByDay(day)
    }

    suspend fun getTodoItemById(itemId: Int): TodoItem {
        return todoDao.getTodoItemById(itemId)
    }

    suspend fun saveTodoItem(todoItem: TodoItem) {
        todoDao.saveTodoItem(todoItem)
    }

    suspend fun saveCompleteTask(completedTask: CompletedTask) {
        todoDao.saveCompletedTask(completedTask)
    }

    */
}