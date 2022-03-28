package com.dmm.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmm.todoapp.R
import com.dmm.todoapp.adapters.TodoListAdapter
import com.dmm.todoapp.data.model.Todo
import com.dmm.todoapp.data.viewmodel.TodoViewModel
import com.dmm.todoapp.databinding.FragmentTodoListBinding


class FragmentTodoList : Fragment() {

    private lateinit var _binding: FragmentTodoListBinding
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by activityViewModels()
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

        binding.fbAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTodoList_to_fragmentAddTodo)
        }

        todoViewModel.allTodo.observe(viewLifecycleOwner, Observer { todoList ->
            todoList.let {
                todoAdapter.submitList(it)
                showIconEmptyList()
            }
        })


    }

    private fun setRecyclerView() = binding.rvTodo.apply {
        var cbListener : (Todo) -> Unit = { todo ->
            todoViewModel.updateTodo(todo, true)
        }
        var cvListener : (Todo) -> Unit = { todo ->
            val todoList = todoViewModel.allTodo.value
            val indexTodo = todoList!!.indexOf(todo)
            val action = FragmentTodoListDirections.actionFragmentTodoListToFragmentDetailTodo(
                indexTodo = indexTodo
            )
            findNavController().navigate(action)
        }
        todoAdapter =  TodoListAdapter(cbListener, cvListener )
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showIconEmptyList() {
        if (todoViewModel.allTodo.value?.isEmpty() == true) {
            binding.emptyList.visibility = View.VISIBLE
        } else {
            binding.emptyList.visibility = View.GONE
        }
    }
}
