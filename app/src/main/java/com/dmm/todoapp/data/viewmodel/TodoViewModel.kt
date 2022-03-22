package com.dmm.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.data.TodoRepository
import com.dmm.todoapp.data.database.TodoDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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

    fun updateTodo(todo: Todo, todoDone: Boolean) {
        viewModelScope.launch {
            todoRepository.updateTodo(todo.copy(todoDone = todoDone))
        }

    }

    fun addTodo(name: String, description: String) {
        viewModelScope.launch {
            val todo = Todo(name = name, description = description ,todoDone = false, date = Calendar.getInstance().time)
            todoRepository.insertTodo(todo)
        }
    }

    fun validateTask(nameTodo: String, descriptionTodo: String): Boolean {
        return !nameTodo.isBlank() && !descriptionTodo.isBlank()
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun getLongCurrentDate(): Long {
        val formatter = SimpleDateFormat("E MM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        return formatter.format(calendar.time).toLong()
    }
}