package com.dmm.todoapp.data

import com.dmm.todoapp.data.database.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    suspend fun addTodo(todo: Todo) {
        todoDao.insert(todo)
    }
}