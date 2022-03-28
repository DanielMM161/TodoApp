package com.dmm.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dmm.todoapp.R
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentAddTodoBinding

class FragmentAddTodo : Fragment() {

    private lateinit var _binding : FragmentAddTodoBinding
    private val binding get() = _binding!!

    private lateinit var viewModel: TodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddTodoBinding.bind(view)

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentAddTodo_to_fragmentTodoList)
        }

        binding.buttonSave.setOnClickListener {
            addTodo()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    private fun addTodo() {
        val title: String = binding.tiTitle.text.toString()
        val description: String = binding.tiDescription.text.toString()

        if(viewModel.validateTask(title, description)) {
            viewModel.addTodo(title, description)
            findNavController().navigate(R.id.action_fragmentAddTodo_to_fragmentTodoList)
        } else {
            binding.tiLayoutTitle.error = getString(R.string.error_title)
            binding.tiLayoutDescription.error = getString(R.string.error_description)
        }
    }
}