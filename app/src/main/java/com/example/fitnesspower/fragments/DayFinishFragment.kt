package com.example.fitnesspower.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnesspower.databinding.FragmentDayFinishBinding
import com.example.fitnesspower.databinding.FragmentWaitingBinding
import com.example.fitnesspower.utils.FragmentManager
import com.example.fitnesspower.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


class DayFinishFragment : Fragment() {

    private var _binding: FragmentDayFinishBinding? = null
    private val binding: FragmentDayFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentDayFinishBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imMain.setImageDrawable(GifDrawable(
            (activity as AppCompatActivity).assets, "congrations.gif")
        )
        binding.bDone.setOnClickListener {
            FragmentManager.setFragment(DaysFragment.newInstance(), activity as AppCompatActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = DayFinishFragment()
    }
}