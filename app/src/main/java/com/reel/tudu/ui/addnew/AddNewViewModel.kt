package com.reel.tudu.ui.addnew

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

class AddNewViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val todoItemLiveData = MutableLiveData<TodoItem>()

    fun saveTodoItem(todoItem: TodoItem?) {
        scope.launch {
            repository.saveTodoItem(todoItem!!)
        }
    }

    fun cacheTodoItem(todoItem: TodoItem?) {
        todoItemLiveData.postValue(todoItem!!)
    }

    fun getCachedTodoItem(): LiveData<TodoItem> {
        return todoItemLiveData.value as LiveData<TodoItem>
    }
}


class AddNewViewModelFactory @Inject constructor(
    addNewViewModelProvider: Provider<AddNewViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        AddNewViewModel::class.java to addNewViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }

}