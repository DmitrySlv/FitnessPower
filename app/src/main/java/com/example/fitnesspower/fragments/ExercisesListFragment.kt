package com.example.fitnesspower.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnesspower.databinding.FragmentExercisesListBinding


class ExercisesListFragment : Fragment() {

    private var _binding: FragmentExercisesListBinding? = null
    private val binding: FragmentExercisesListBinding
        get() = _binding ?: throw RuntimeException("FragmentExercisesListBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExercisesListFragment()
    }
}