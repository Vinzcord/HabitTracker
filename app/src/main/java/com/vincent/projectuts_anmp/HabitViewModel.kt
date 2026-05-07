package com.vincent.projectuts_anmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HabitViewModel : ViewModel() {
    val habits: LiveData<MutableList<Habit>> = HabitListAdapter.habits

    fun addHabit(habit: Habit) {
        HabitListAdapter.addHabit(habit)
    }

    fun incrementProgress(index: Int) {
        HabitListAdapter.updateHabitProgress(index, 1)
    }

    fun decrementProgress(index: Int) {
        HabitListAdapter.updateHabitProgress(index, -1)
    }
}