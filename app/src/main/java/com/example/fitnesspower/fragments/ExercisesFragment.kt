package com.example.fitnesspower.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesspower.R
import com.example.fitnesspower.adapters.ExerciseAdapter
import com.example.fitnesspower.databinding.FragmentExerciseBinding
import com.example.fitnesspower.databinding.FragmentExercisesListBinding
import com.example.fitnesspower.fragments.WaitingFragment.Companion.COUNT_DOWN_TIME
import com.example.fitnesspower.models.ExerciseModel
import com.example.fitnesspower.utils.FragmentManager
import com.example.fitnesspower.utils.TimeUtils
import com.example.fitnesspower.viewModels.MainViewModel
import pl.droidsonroids.gif.GifDrawable


class ExercisesFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding: FragmentExerciseBinding
        get() = _binding ?: throw RuntimeException("FragmentExerciseBinding is null")

    private val model: MainViewModel by activityViewModels()
    private var exerciseCounter = 0
    private var exercisesList: ArrayList<ExerciseModel>? = null
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.listExercise.observe(viewLifecycleOwner) {
            exercisesList = it
            nextExercise()
        }
        binding.bNext.setOnClickListener {
            nextExercise()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        timer?.cancel()
    }

    private fun nextExercise() {
        if (exerciseCounter < exercisesList?.size!!) {
            val ex = exercisesList?.get(exerciseCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
            showNextExercise()
        } else {
            FragmentManager.setFragment(DayFinishFragment.newInstance(), activity as AppCompatActivity)
        }
    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
    }

    private fun setExerciseType(exercise: ExerciseModel) = with(binding) {
        if (exercise.time.startsWith("x")) {
            tvTime.text = exercise.time
            timer?.cancel()
            progressBar.progress = 0
        } else {
            startTimer(exercise)
        }
    }

    private fun showNextExercise() = with(binding) {
        if (exerciseCounter < exercisesList?.size!!) {
            val ex = exercisesList?.get(exerciseCounter) ?: return
            imNext.setImageDrawable(GifDrawable(root.context.assets, ex.image))
            setTimeType(ex)
        } else {
            imNext.setImageDrawable(GifDrawable(root.context.assets, "congrations2.gif"))
            tvNextName.text = getText(R.string.done)
        }
    }

    private fun setTimeType(ex: ExerciseModel) = with(binding) {
        if (ex.time.startsWith("x")) {
            tvNextName.text = String.format(ex.name + ": " + ex.time)
        } else {
            val name = ex.name + ": ${TimeUtils.getTime(ex.time.toLong() * 1000)}"
            tvNextName.text = name
        }
    }

    private fun startTimer(exercise: ExerciseModel) = with(binding) {
        progressBar.max = exercise.time.toInt() * 1000
        timer?.cancel()
        timer = object: CountDownTimer(exercise.time.toLong() * 1000, 1) {

            override fun onTick(restTime: Long) {
                tvTime.text = TimeUtils.getTime(restTime)
                progressBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                nextExercise()
            }
        }.start()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExercisesFragment()
    }
}