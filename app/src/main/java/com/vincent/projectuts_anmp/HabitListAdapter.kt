package com.vincent.projectuts_anmp

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vincent.anmp_projectuts.databinding.ItemHabitBinding

class HabitListAdapter(
    private val onPlusClick: (Int) -> Unit,
    private val onMinusClick: (Int) -> Unit
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    private var habits: List<Habit> = emptyList()

    fun submitList(newHabits: List<Habit>) {
        habits = newHabits
        notifyDataSetChanged()
    }

    class HabitViewHolder(val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        with(holder.binding) {
            txtHabitName.text = habit.name
            txtDescription.text = habit.description
            txtProgressCount.text = "${habit.currentProgress} / ${habit.goal} ${habit.unit}"
            
            val progress = if (habit.goal > 0) (habit.currentProgress * 100 / habit.goal) else 0
            progressBar.progress = progress

            val colorRes = if (habit.currentProgress >= habit.goal && habit.goal > 0) {
                txtStatus.text = "Completed"
                android.R.color.holo_green_light
            } else {
                txtStatus.text = "In Progress"
                android.R.color.darker_gray
            }
            txtStatus.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(root.context, colorRes))

            btnPlus.setOnClickListener { 
                val currentPos = holder.adapterPosition
                if (currentPos != RecyclerView.NO_POSITION) onPlusClick(currentPos) 
            }
            btnMinus.setOnClickListener { 
                val currentPos = holder.adapterPosition
                if (currentPos != RecyclerView.NO_POSITION) onMinusClick(currentPos) 
            }
        }
    }

    override fun getItemCount(): Int = habits.size
}