package com.dmm.todoapp.adapters

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.R
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.databinding.ItemTodoListBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox


class TodoListAdapter(
    private val onCheckBoxClicked: (Todo) -> Unit,
    private val onCardViewClicked:(Todo) -> Unit
) : ListAdapter<Todo, TodoListAdapter.TodoListViewHolder>(diffCallback) {

    class TodoListViewHolder(private var binding: ItemTodoListBinding, private var onCheckBoxClicked: (Todo) -> Unit, private var onCardViewClicked: (Todo) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            if(todo.todoDone) {
                val string = SpannableString(todo.name)
                string.setSpan(StrikethroughSpan(), 0, string.length, 0)
                binding.cbTodo.text = string
                binding.cbTodo.isChecked = true
            } else {
                binding.tvNameTodo.text = todo.name
                binding.tvMultiline.text = todo.description
            }

            binding.cbTodo.setOnClickListener {  onCheckBoxClicked(todo) }
            binding.materialCardView.setOnClickListener { onCardViewClicked(todo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapter.TodoListViewHolder {
        val binding =  ItemTodoListBinding.inflate(LayoutInflater.from(parent.context))
        return TodoListViewHolder(binding, onCheckBoxClicked, onCardViewClicked)

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
        holder.bind(itemCurrent)
    }
}


