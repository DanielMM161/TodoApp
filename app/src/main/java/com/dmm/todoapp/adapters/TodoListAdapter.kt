package com.dmm.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.databinding.ItemTodoListBinding

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.TodoListAdapterViewHolder>() {

    inner class TodoListAdapterViewHolder(val binding: ItemTodoListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapterViewHolder {
        return TodoListAdapterViewHolder(ItemTodoListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todoList: List<Todo>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun onBindViewHolder(holder: TodoListAdapterViewHolder, position: Int) {
        holder.binding.apply {
            val item = todoList[position]
            cbTodo.text = item.name
        }
    }

    override fun getItemCount() = todoList.size
}