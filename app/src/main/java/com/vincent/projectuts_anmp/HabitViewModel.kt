package com.vincent.projectuts_anmp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).habitDao()
    val habits: LiveData<List<Habit>> = dao.getAllHabits().asLiveData()

    fun insert(habit: Habit) = viewModelScope.launch {
        dao.insertHabit(habit)
    }

    fun update(habit: Habit) = viewModelScope.launch {
        dao.updateHabit(habit)
    }

    fun getHabitById(id: Int): LiveData<Habit?> {
        val habit = MutableLiveData<Habit?>()
        viewModelScope.launch {
            habit.value = dao.getHabitById(id)
        }
        return habit
    }

    fun updateProgress(habit: Habit, delta: Int) = viewModelScope.launch {
        val newProgress = habit.currentProgress + delta
        if (newProgress in 0..habit.goal) {
            habit.currentProgress = newProgress
            dao.updateHabit(habit)
        }
    }
}
