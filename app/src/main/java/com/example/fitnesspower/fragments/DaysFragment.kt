package com.example.fitnesspower.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesspower.R
import com.example.fitnesspower.adapters.DaysAdapter
import com.example.fitnesspower.databinding.FragmentDaysBinding
import com.example.fitnesspower.models.DayModel


class DaysFragment : Fragment() {

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
        val adapter = DaysAdapter()
        rcViewDays.layoutManager = LinearLayoutManager(requireActivity())
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

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}