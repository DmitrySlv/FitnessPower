package com.example.fitnesspower.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fitnesspower.R
import com.example.fitnesspower.databinding.DaysListItemBinding
import com.example.fitnesspower.models.DayModel

class DaysAdapter: ListAdapter<DayModel, DaysAdapter.DayHolder>(DiffUtilDaysAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.days_list_item,parent,false)
        return DayHolder(view)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class DayHolder(view: View): ViewHolder(view) {
        private val binding = DaysListItemBinding.bind(view)

        fun setData(dayModel: DayModel) = with(binding) {
            val name = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            tvName.text = name
            val exCounter = dayModel.exercises.split(",").size.toString()
            tvCounter.text = exCounter
        }
    }
}