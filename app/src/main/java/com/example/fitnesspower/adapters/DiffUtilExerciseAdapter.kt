package com.example.fitnesspower.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.fitnesspower.models.DayModel
import com.example.fitnesspower.models.ExerciseModel

object DiffUtilExerciseAdapter: DiffUtil.ItemCallback<ExerciseModel>() {

    override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
        return oldItem == newItem
    }
}