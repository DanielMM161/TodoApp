package com.dmm.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.R
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.databinding.ItemTodoListBinding
import com.google.android.material.checkbox.MaterialCheckBox

class TodoListAdapter(private val onItemClicked: (Todo) -> Unit) :
    ListAdapter<Todo, TodoListAdapter.TodoListViewHolder>(diffCallback) {

    class TodoListViewHolder(private var binding: ItemTodoListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.cbTodo.text = todo.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapter.TodoListViewHolder {
        return TodoListViewHolder(
            ItemTodoListBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: TodoListAdapter.TodoListViewHolder, position: Int) {
        val itemCurrent = getItem(position)
        val checkBox = holder.itemView.findViewById(R.id.cb_todo) as MaterialCheckBox
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            onItemClicked(itemCurrent)
        }
        holder.bind(itemCurrent)
    }
}