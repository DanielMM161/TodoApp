package com.dmm.todoapp.adapters

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.databinding.ItemTodoListBinding
import com.dmm.todoapp.utils.Utils

class TodoListAdapter(
    private val onCheckBoxClicked: (Todo) -> Unit,
    private val onCardViewClicked:(Todo) -> Unit
) : ListAdapter<Todo, TodoListAdapter.TodoListViewHolder>(diffCallback) {

    class TodoListViewHolder(private var binding: ItemTodoListBinding, private var onCheckBoxClicked: (Todo) -> Unit, private var onCardViewClicked: (Todo) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            var date = Utils.convertDateToFormattedDate(todo.date)

            if(todo.todoDone) {
                val string = SpannableString(date)
                string.setSpan(StrikethroughSpan(), 0, string.length, 0)
                binding.cbTodo.text = string
                binding.cbTodo.isChecked = true
            } else {
                binding.cbTodo.text = date
            }

            binding.tvNameTodo.text = todo.name
            binding.tvMultiline.text = todo.description
            
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


