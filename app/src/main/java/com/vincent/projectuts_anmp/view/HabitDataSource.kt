package com.vincent.projectuts_anmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vincent.projectuts_anmp.model.Habit

object HabitRepository {
    private val _habits = MutableLiveData<MutableList<Habit>>(mutableListOf())
    val habits: LiveData<MutableList<Habit>> = _habits

    fun addHabit(habit: Habit) {
        val currentList = _habits.value ?: mutableListOf()
        val newId = if (currentList.isEmpty()) 1 else currentList.maxOf { it.id } + 1
        val habitWithId = habit.copy(id = newId)
        currentList.add(habitWithId)
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

    fun getHabitById(id: Int): LiveData<Habit?> {
        val habit = MutableLiveData<Habit?>()
        habit.value = _habits.value?.find { it.id == id }
        return habit
    }

    fun updateHabit(updatedHabit: Habit) {
        val currentList = _habits.value ?: return
        val index = currentList.indexOfFirst { it.id == updatedHabit.id }
        if (index != -1) {
            currentList[index] = updatedHabit
            _habits.value = currentList
        }
    }
}