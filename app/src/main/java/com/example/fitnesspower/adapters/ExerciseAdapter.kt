package com.example.fitnesspower.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fitnesspower.R
import com.example.fitnesspower.databinding.ExerciseListItemBinding
import com.example.fitnesspower.models.ExerciseModel
import pl.droidsonroids.gif.GifDrawable

class ExerciseAdapter: ListAdapter<ExerciseModel, ExerciseAdapter.ExerciseHolder>(DiffUtilExerciseAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_list_item,parent,false)
        return ExerciseHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class ExerciseHolder(view: View): ViewHolder(view) {
        private val binding = ExerciseListItemBinding.bind(view)

        fun setData(exercise: ExerciseModel) = with(binding) {
            tvName.text = exercise.name
            tvCount.text = exercise.time
            imExercise.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        }
    }

    companion object {
    }
}