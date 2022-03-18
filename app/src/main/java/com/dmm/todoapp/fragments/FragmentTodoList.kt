package com.dmm.todoapp.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.R
import com.dmm.todoapp.adapters.TodoListAdapter
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentTodoListBinding
import com.google.android.material.textfield.TextInputEditText


class FragmentTodoList : Fragment() {

    private lateinit var _binding: FragmentTodoListBinding
    private val binding get() = _binding!!

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodoListBinding.bind(view)
        setRecyclerView()

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.fbAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTodoList_to_fragmentAddTodo)
        }

        todoViewModel.allTodo.observe(viewLifecycleOwner, Observer { todoList ->
            todoList.let {
                todoAdapter.submitList(it)
            }
        })
    }

    private fun setRecyclerView() = binding.rvTodo.apply {
        todoAdapter =  TodoListAdapter { it ->
            todoViewModel.updateTodo(it, true)
        }
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}
