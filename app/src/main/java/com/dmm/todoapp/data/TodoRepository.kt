package com.dmm.todoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.dmm.todoapp.data.database.TodoDao
import com.dmm.todoapp.data.model.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val allTodo: LiveData<List<Todo>> = todoDao.getAllTodo().asLiveData()

    suspend fun insertTodo(todo: Todo) {
        todoDao.insert(todo)
    }
}