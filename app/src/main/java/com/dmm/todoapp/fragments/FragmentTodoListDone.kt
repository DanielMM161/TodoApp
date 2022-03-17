package com.dmm.todoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.R
import com.dmm.todoapp.adapters.TodoListAdapter
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentTodoListDoneBinding
import kotlinx.coroutines.launch

class FragmentTodoListDone : Fragment() {

    private lateinit var _binding: FragmentTodoListDoneBinding
    private val binding get() = _binding!!

    private lateinit var todoViewModel: TodoViewModel
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

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        todoViewModel.allTodoDone.observe(viewLifecycleOwner, Observer { todoList ->
            todoList.let {
                todoAdapter.submitList(it)
            }
        })

    }

    private fun setupRecyclerView() = binding.rvTodoDone.apply {
        todoAdapter = TodoListAdapter { it ->
            todoViewModel.updateTodo(it, false)
        }
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}