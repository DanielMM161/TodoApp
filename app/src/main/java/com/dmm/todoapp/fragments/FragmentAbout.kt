package com.dmm.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmm.todoapp.R
import com.dmm.todoapp.databinding.FragmentAboutBinding
import com.dmm.todoapp.databinding.FragmentTodoListDoneBinding

class FragmentAbout : Fragment() {

    private lateinit var _binding : FragmentAboutBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAboutBinding.bind(view)
    }
}