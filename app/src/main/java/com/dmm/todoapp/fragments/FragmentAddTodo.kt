package com.dmm.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmm.todoapp.R
import com.dmm.todoapp.databinding.FragmentAddTodoBinding

class FragmentAddTodo : Fragment() {

    private lateinit var _binding : FragmentAddTodoBinding
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddTodoBinding.bind(view)

        binding.buttonCancel.setOnClickListener {

        }

        binding.buttonSave.setOnClickListener {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }
}