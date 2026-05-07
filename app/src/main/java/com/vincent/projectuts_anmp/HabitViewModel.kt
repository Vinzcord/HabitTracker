package com.vincent.projectuts_anmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HabitViewModel : ViewModel() {
    val habits: LiveData<MutableList<Habit>> = HabitRepository.habits

    fun addHabit(habit: Habit) {
        HabitRepository.addHabit(habit)
    }

    fun incrementProgress(index: Int) {
        HabitRepository.updateHabitProgress(index, 1)
    }

    fun decrementProgress(index: Int) {
        HabitRepository.updateHabitProgress(index, -1)
    }
}