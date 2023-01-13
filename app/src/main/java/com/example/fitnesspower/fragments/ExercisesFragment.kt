package com.example.fitnesspower.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesspower.adapters.ExerciseAdapter
import com.example.fitnesspower.databinding.FragmentExerciseBinding
import com.example.fitnesspower.databinding.FragmentExercisesListBinding
import com.example.fitnesspower.models.ExerciseModel
import com.example.fitnesspower.utils.FragmentManager
import com.example.fitnesspower.viewModels.MainViewModel
import pl.droidsonroids.gif.GifDrawable


class ExercisesFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding: FragmentExerciseBinding
        get() = _binding ?: throw RuntimeException("FragmentExerciseBinding is null")

    private val model: MainViewModel by activityViewModels()
    private var exerciseCounter = 0
    private var exercisesList: ArrayList<ExerciseModel>? = null

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

    private fun nextExercise() {
        if (exerciseCounter < exercisesList?.size!!) {
            val ex = exercisesList?.get(exerciseCounter++)
            showExercise(ex)
        } else {
            Toast.makeText(activity, "Конец", Toast.LENGTH_LONG).show()
        }
    }

    private fun showExercise(exercise: ExerciseModel?) = with(binding) {
        if (exercise == null) return@with
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExercisesFragment()
    }
}