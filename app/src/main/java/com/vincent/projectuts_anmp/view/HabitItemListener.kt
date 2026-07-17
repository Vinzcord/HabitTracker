package com.vincent.projectuts_anmp

import com.vincent.projectuts_anmp.model.Habit

interface HabitItemListener {
    fun onPlusClick(habit: Habit)
    fun onMinusClick(habit: Habit)
    fun onEditClick(habit: Habit)
}
