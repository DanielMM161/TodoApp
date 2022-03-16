package com.dmm.todoapp.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
            showDialog()
        }

        todoViewModel.allTodo.observe(viewLifecycleOwner, Observer { todoList ->
            todoAdapter.todoList = todoList
        })
    }

    private fun setRecyclerView() = binding.rvTodo.apply {
        todoAdapter = TodoListAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    fun showDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog, null)
        val editText: TextInputEditText = dialogLayout.findViewById(R.id.task_input_text)

        builder.setView(dialogLayout)
            .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
                val todoName: String = editText.text.toString()
                if(todoViewModel.validateNameTodo(todoName)) {
                    todoViewModel.addTodo(todoName)
                }
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        builder.create()
        builder.show()
    }
}