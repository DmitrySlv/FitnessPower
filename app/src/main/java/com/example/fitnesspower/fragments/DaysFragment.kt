package com.example.fitnesspower.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesspower.R
import com.example.fitnesspower.adapters.DaysAdapter
import com.example.fitnesspower.databinding.FragmentDaysBinding
import com.example.fitnesspower.models.DayModel
import com.example.fitnesspower.models.ExerciseModel
import com.example.fitnesspower.utils.FragmentManager


class DaysFragment: Fragment(), DaysAdapter.Listener {

    private var _binding: FragmentDaysBinding? = null
    private val binding: FragmentDaysBinding
        get() = _binding ?: throw RuntimeException("FragmentDaysBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = arrayListOf<DayModel>()
        resources.getStringArray(R.array.day_exercises).forEach {
            tempArray.add(DayModel(it, false))
        }
        return tempArray
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = arrayListOf<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercises)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], exerciseArray[2]))
        }
    }

    override fun onClick(day: DayModel) {
        fillExerciseList(day)
        FragmentManager.setFragment(
            ExercisesListFragment.newInstance(), activity as AppCompatActivity
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}