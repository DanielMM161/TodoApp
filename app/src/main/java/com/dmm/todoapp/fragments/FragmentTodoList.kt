package com.dmm.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dmm.todoapp.R
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentTodoListBinding

class FragmentTodoList : Fragment() {

    private lateinit var _binding: FragmentTodoListBinding
    private val binding get() = _binding!!

    private lateinit var todoViewModel: TodoViewModel

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

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.buttonTest.setOnClickListener {
            todoViewModel.addTodo("TEST")
        }
    }
}