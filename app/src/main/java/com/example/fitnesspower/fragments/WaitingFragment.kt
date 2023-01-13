package com.example.fitnesspower.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnesspower.databinding.FragmentWaitingBinding
import com.example.fitnesspower.utils.FragmentManager
import com.example.fitnesspower.utils.TimeUtils


class WaitingFragment : Fragment() {

    private var _binding: FragmentWaitingBinding? = null
    private val binding: FragmentWaitingBinding
        get() = _binding ?: throw RuntimeException("FragmentWaitingBinding is null")

    lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWaitingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pBar.max = COUNT_DOWN_TIME.toInt()
        startTimer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    private fun startTimer() = with(binding) {
        timer = object: CountDownTimer(COUNT_DOWN_TIME, 1) {

            override fun onTick(restTime: Long) {
                tvTimer.text = TimeUtils.getTime(restTime)
                pBar.progress = restTime.toInt()
            }

            override fun onFinish() {
               FragmentManager.setFragment(
                   ExercisesFragment.newInstance(),
                   activity as AppCompatActivity
               )
            }
        }.start()
    }

    companion object {

        @JvmStatic
        fun newInstance() = WaitingFragment()

        const val COUNT_DOWN_TIME = 6000L
    }
}