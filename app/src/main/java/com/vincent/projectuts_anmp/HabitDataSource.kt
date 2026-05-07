package com.vincent.projectuts_anmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object HabitRepository {
    private val _habits = MutableLiveData<MutableList<Habit>>(mutableListOf())
    val habits: LiveData<MutableList<Habit>> = _habits

    fun addHabit(habit: Habit) {
        val currentList = _habits.value ?: mutableListOf()
        currentList.add(habit)
        _habits.value = currentList
    }

    fun updateHabitProgress(index: Int, delta: Int) {
        val currentList = _habits.value ?: return
        if (index in currentList.indices) {
            val habit = currentList[index]
            habit.currentProgress = (habit.currentProgress + delta).coerceIn(0, habit.goal)
            _habits.value = currentList
        }
    }
}