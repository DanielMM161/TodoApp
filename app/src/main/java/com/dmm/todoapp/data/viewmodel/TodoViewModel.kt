package com.dmm.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.todoapp.data.Todo
import com.dmm.todoapp.data.TodoRepository
import com.dmm.todoapp.data.database.TodoDatabase
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        todoRepository = TodoRepository(todoDao)
    }

    fun addTodo(name: String) {
        viewModelScope.launch {
            val todo = Todo(name = name, todoDone = false)
            todoRepository.addTodo(todo)
        }
    }
}