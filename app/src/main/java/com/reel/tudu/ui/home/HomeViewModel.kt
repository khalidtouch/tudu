package com.reel.tudu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reel.tudu.data.repositories.TodoRepository
import com.reel.tudu.entities.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import kotlin.coroutines.CoroutineContext

class HomeViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val todoItemsLiveData = MutableLiveData<List<TodoItem>>()
    private val completedTasksLiveData = MutableLiveData<List<TodoItem>>()
    private val todoItemLiveData = MutableLiveData<TodoItem>()
    private val completedTaskLiveData = MutableLiveData<TodoItem>()

    fun getAllTodoItems(): LiveData<List<TodoItem>> {
        scope.launch {
            todoItemsLiveData.postValue(repository.getAllTodoItems())
        }
        return todoItemsLiveData
    }

    fun getAllCompletedTasks(): LiveData<List<TodoItem>> {
        scope.launch {
            completedTasksLiveData.postValue(repository.getAllCompletedTasks())
        }
        return completedTasksLiveData
    }

    fun getTodoItemById(itemId: Int): LiveData<TodoItem> {
        scope.launch {
            todoItemLiveData.postValue(repository.getTodoItemById(itemId))
        }
        return todoItemLiveData
    }


    fun saveTodoItem(todoItem: TodoItem) {
        scope.launch {
            repository.saveTodoItem(todoItem)
        }
    }


}

//@Suppress("UNCHECKED_CAST")
//class HomeViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Not found ")
//    }
//
//}


class HomeViewModelFactory @Inject constructor(
    homeViewModelProvider: Provider<HomeViewModel>
) : ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        HomeViewModel::class.java to homeViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }

}
