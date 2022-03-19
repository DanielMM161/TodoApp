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

    private fun addNewEntry(name: String, todoDone: Boolean): Todo {
        return Todo(
            name = name,
            description = "",
            todoDone = todoDone
        )
    }

    fun updateTodo(todo: Todo, todoDone: Boolean) {
        viewModelScope.launch {
            todoRepository.updateTodo(todo.copy(todoDone = todoDone))
        }

    }

    fun addTodo(name: String, description: String) {
        viewModelScope.launch {
            val todo = Todo(name = name, description = description ,todoDone = false)
            todoRepository.insertTodo(todo)
        }
    }

    fun validateTask(nameTodo: String, descriptionTodo: String): Boolean {
        return !nameTodo.isBlank() && !descriptionTodo.isBlank()
    }
}