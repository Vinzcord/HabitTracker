package com.vincent.projectuts_anmp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vincent.projectuts_anmp.HabitRepository
import com.vincent.projectuts_anmp.model.Habit

class HabitViewModel : ViewModel() {
    val habits: LiveData<MutableList<Habit>> = HabitRepository.habits

    fun getHabitById(id: Int): LiveData<Habit?> {
        return HabitRepository.getHabitById(id)
    }

    fun update(habit: Habit) {
        HabitRepository.updateHabit(habit)
    }

    fun insert(habit: Habit) {
        HabitRepository.addHabit(habit)
    }

    fun updateProgress(habit: Habit, delta: Int) {
        val currentList = habits.value ?: return
        val index = currentList.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            HabitRepository.updateHabitProgress(index, delta)
        }
    }
}
