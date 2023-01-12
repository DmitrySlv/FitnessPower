package com.example.fitnesspower.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesspower.adapters.ExerciseAdapter
import com.example.fitnesspower.databinding.FragmentExercisesListBinding
import com.example.fitnesspower.utils.FragmentManager
import com.example.fitnesspower.viewModels.MainViewModel


class ExercisesListFragment : Fragment() {

    private var _binding: FragmentExercisesListBinding? = null
    private val binding: FragmentExercisesListBinding
        get() = _binding ?: throw RuntimeException("FragmentExercisesListBinding is null")

    private val model: MainViewModel by activityViewModels()
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.listExercise.observe(viewLifecycleOwner) {
          exerciseAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() = with(binding) {
        exerciseAdapter = ExerciseAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = exerciseAdapter
        bStart.setOnClickListener {
            FragmentManager.setFragment(WaitingFragment.newInstance(), activity as AppCompatActivity)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExercisesListFragment()
    }
}