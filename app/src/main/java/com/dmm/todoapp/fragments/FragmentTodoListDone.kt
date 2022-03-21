package com.dmm.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmm.todoapp.R
import com.dmm.todoapp.adapters.TodoListAdapter
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentTodoListDoneBinding

class FragmentTodoListDone : Fragment() {

    private lateinit var _binding: FragmentTodoListDoneBinding
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by activityViewModels()
    private lateinit var todoAdapter: TodoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodoListDoneBinding.bind(view)
        setupRecyclerView()

        todoViewModel.allTodoDone.observe(viewLifecycleOwner, Observer { todoList ->
            todoList.let {
                todoAdapter.submitList(it)
            }
        })

    }

    private fun setupRecyclerView() = binding.rvTodoDone.apply {
        var cbListener : (Todo) -> Unit = { todo -> todoViewModel.updateTodo(todo, false) }
        var cvListener : (Todo) -> Unit = { todo -> }

        todoAdapter = TodoListAdapter(cbListener, cvListener)
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}