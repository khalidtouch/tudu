package com.reel.tudu.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reel.tudu.data.repositories.TodoRepository
import com.reel.tudu.entities.CompletedTask
import com.reel.tudu.entities.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import kotlin.coroutines.CoroutineContext

class HomeViewModel : ViewModel() {
    private val todoRepository = TodoRepository()
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val todoItemsLiveData = MutableLiveData<List<TodoItem>>()
    private val completedTasksLiveData = MutableLiveData<List<CompletedTask>>()
    private val todoItemLiveData = MutableLiveData<TodoItem>()
    private val completedTaskLiveData = MutableLiveData<CompletedTask>()

    fun getAllTodoItems(): LiveData<List<TodoItem>> {
        scope.launch {
            todoItemsLiveData.postValue(todoRepository.getAllTodoItems())
        }
        return todoItemsLiveData
    }
/**
    fun getAllCompletedTasks(): LiveData<List<CompletedTask>> {
        scope.launch {
            completedTasksLiveData.postValue(todoRepository.getAllCompletedTasks())
        }
        return completedTasksLiveData
    }

    fun getTodoItemById(itemId: Int): LiveData<TodoItem> {
        scope.launch {
            todoItemLiveData.postValue(todoRepository.getTodoItemById(itemId))
        }
        return todoItemLiveData
    }

    fun getCompletedTaskByDay(day: Int): LiveData<CompletedTask> {
        scope.launch {
            completedTaskLiveData.postValue(todoRepository.getCompletedTaskByDay(day))
        }
        return completedTaskLiveData
    }

    fun saveTodoItem(todoItem: TodoItem) {
        scope.launch {
            todoRepository.saveTodoItem(todoItem)
        }
    }

    fun saveCompletedTask(completedTask: CompletedTask) {
        scope.launch {
            todoRepository.saveCompleteTask(completedTask)
        }
    }
    */
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Not found ")
    }

}