package com.example.fitnesspower.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnesspower.databinding.FragmentWaitingBinding


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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startTimer() = with(binding) {
        timer = object: CountDownTimer(COUNT_DOWN_TIME, 100) {

            override fun onTick(restTime: Long) {
               pBar.progress = restTime.toInt()
            }

            override fun onFinish() {
            }
        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {

        @JvmStatic
        fun newInstance() = WaitingFragment()

        const val COUNT_DOWN_TIME = 11000L
    }
}