package com.example.fitnesspower.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesspower.R
import com.example.fitnesspower.adapters.DaysAdapter
import com.example.fitnesspower.databinding.FragmentDaysBinding
import com.example.fitnesspower.models.DayModel
import com.example.fitnesspower.models.ExerciseModel
import com.example.fitnesspower.utils.FragmentManager
import com.example.fitnesspower.viewModels.MainViewModel


class DaysFragment: Fragment(), DaysAdapter.Listener {

    private var _binding: FragmentDaysBinding? = null
    private val binding: FragmentDaysBinding
        get() = _binding ?: throw RuntimeException("FragmentDaysBinding is null")

    private var actionBar: ActionBar? = null
    private val model: MainViewModel by activityViewModels()
    private lateinit var adapter: DaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentDay = 0
        initRcView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       return inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reset) {
            model.pref?.edit()?.clear()?.apply()
            adapter.submitList(fillDaysArray())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRcView() = with(binding) {
        adapter = DaysAdapter(this@DaysFragment)
        actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.days)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = arrayListOf<DayModel>()
        var daysDoneCounter = 0
        resources.getStringArray(R.array.day_exercises).forEach {
            model.currentDay++
            val exCounter = it.split(",").size
            tempArray.add(DayModel(it, 0, model.getExerciseCount() == exCounter))
        }
        binding.pBar.max = tempArray.size
        tempArray.forEach {
            if (it.isDone) daysDoneCounter++
        }
        updateRestDaysUI(tempArray.size - daysDoneCounter, tempArray.size)
        return tempArray
    }

    private fun updateRestDaysUI(restDays: Int, days: Int) = with(binding) {
        val rDays = getString(R.string.rest) + " $restDays " + getString(R.string.rest_days)
        tvRetDays.text = rDays
        pBar.progress = days - restDays
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = arrayListOf<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercises)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], false, exerciseArray[2]))
        }
        model.listExercise.value = tempList
        model.listExercise.observe(viewLifecycleOwner) {
        }
    }

    override fun onClick(day: DayModel) {
        fillExerciseList(day)
        model.currentDay = day.dayNumber
        FragmentManager.setFragment(
            ExercisesListFragment.newInstance(), activity as AppCompatActivity
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}