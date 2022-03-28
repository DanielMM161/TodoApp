package com.dmm.todoapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dmm.todoapp.R
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentDetailTodoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FragmentDetailTodo : Fragment() {

    private lateinit var _binding : FragmentDetailTodoBinding
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by activityViewModels()
    private lateinit var todoSelected: Todo
    private var indextTodo: Int = -1

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailTodoBinding.bind(view)

        arguments?.let {
            indextTodo = it.getInt("indexTodo")
        }

        setTextInputs()

        binding.buttonEdit.setOnClickListener {
            val todo: Todo = todoSelected.copy(name =  binding.tiTitle.text.toString(), description =  binding.tiDescription.text.toString())
            todoViewModel.updateTodo(todo, todoSelected.todoDone)
            findNavController().navigate(R.id.action_fragmentDetailTodo_to_fragmentTodoList)
        }

        binding.buttonDelete.setOnClickListener {
            showDialog()
        }

        binding.tiTitle.addTextChangedListener { _ ->
            enabledButtonEdit()
        }
        binding.tiDescription.addTextChangedListener { _ ->
            enabledButtonEdit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_todo, container, false)
    }

    private fun setTextInputs() {
        todoSelected = todoViewModel.allTodo.value?.get(indextTodo)!!
        binding.tiTitle.setText(todoSelected.name)
        binding.tiDescription.setText(todoSelected.description)
    }

    private fun enabledButtonEdit() {
        val textTitle: String = binding.tiTitle.text.toString().trim()
        val textDescription: String = binding.tiDescription.text.toString().trim()
        binding.buttonEdit.isEnabled = (textTitle != todoSelected.name.trim() || textDescription != todoSelected.description.trim()) && (!textTitle.isBlank() || !textDescription.isBlank())
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("${getString(R.string.delete_dialog, todoSelected.name)}")
            .setMessage("${getString(R.string.message_dialog, todoSelected.name)}")
            .setNegativeButton("${getString(R.string.no_dialog)}") { _, _ -> }
            .setPositiveButton("${getString(R.string.yes_dialog)}") { _, _ ->
                todoViewModel.deleteTodo(todoSelected)
                findNavController().navigate(R.id.action_fragmentDetailTodo_to_fragmentTodoList)
            }
            .show()
    }
}