package com.dmm.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.data.TodoRepository
import com.dmm.todoapp.data.database.TodoDatabase
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val allTodo: LiveData<List<Todo>>
    val allTodoDone: LiveData<List<Todo>>
    private val todoRepository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        todoRepository = TodoRepository(todoDao)
        allTodo = todoRepository.allTodo
        allTodoDone = todoRepository.todoDone
    }

    fun addTodo(name: String) {
        viewModelScope.launch {
            val todo = Todo(name = name, todoDone = false)
            todoRepository.insertTodo(todo)
        }
    }

    fun validateNameTodo(nameTodo: String): Boolean {
        return !nameTodo.isBlank()
    }
}