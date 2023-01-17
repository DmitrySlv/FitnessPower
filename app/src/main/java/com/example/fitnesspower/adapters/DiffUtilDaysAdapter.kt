package com.example.fitnesspower.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.fitnesspower.models.DayModel

object DiffUtilDaysAdapter: DiffUtil.ItemCallback<DayModel>() {

    override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
        return oldItem == newItem
    }
}