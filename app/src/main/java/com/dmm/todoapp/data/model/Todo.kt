package com.dmm.todoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo_table")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "todo_name")
    val name: String,
    @ColumnInfo(name = "todo_description")
    val description: String,
    @ColumnInfo(name = "todo_done")
    val todoDone: Boolean,
    @ColumnInfo(name = "date")
    val date: Date
        )