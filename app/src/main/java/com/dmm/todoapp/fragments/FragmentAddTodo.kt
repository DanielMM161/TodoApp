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

        titleFocusListener()
        descriptionFocusListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    private fun addTodo() {
        var titleTask = getTitleTask()
        var descriptionTask = getDescriptionTask()

        if(viewModel.validateTask(titleTask, descriptionTask)) {
            viewModel.addTodo(titleTask, titleTask)
            findNavController().navigate(R.id.action_fragmentAddTodo_to_fragmentTodoList)
        }

        binding.tiLayoutTitle.error = validateTitle()

        binding.tiLayoutDescription.error = validateDescription()
    }

    private fun titleFocusListener() {
        binding.tiTitle.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.tiLayoutTitle.error = validateTitle()
            }
        }
    }

    private fun descriptionFocusListener() {
        binding.tiDescription.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.tiLayoutDescription.error = validateDescription()
            }
        }
    }

    private fun validateTitle(): String? {
        var titleTask = getTitleTask()

        if(titleTask.length > 35) {
            return "Maximo de caracteres 35"
        }

        if(titleTask.isEmpty()) {
            return getString(R.string.error_title)
        }

        return null
    }

    private fun validateDescription(): String? {
        var descriptionTask = getDescriptionTask()

        if(descriptionTask.length > 200) {
            return "Maximo de caracteres 200"
        }

        if(descriptionTask.isEmpty()) {
            return getString(R.string.error_description)
        }

        return null
    }

    private fun getTitleTask(): String {
        return binding.tiTitle.text.toString()
    }

    private fun getDescriptionTask(): String {
        return binding.tiDescription.text.toString()
    }
}