package com.vincent.projectuts_anmp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vincent.anmp_projectuts.databinding.ItemHabitBinding

class HabitListAdapter(
    private val listener: HabitItemListener
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
        holder.binding.habit = habits[position]
        holder.binding.listener = listener
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = habits.size
}
