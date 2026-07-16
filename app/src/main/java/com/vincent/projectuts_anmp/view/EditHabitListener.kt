package com.vincent.projectuts_anmp

import com.vincent.projectuts_anmp.model.Habit

interface EditHabitListener {
    fun onSubmit(habit: Habit)
}
