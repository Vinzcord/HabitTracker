package com.vincent.projectuts_anmp

interface HabitItemListener {
    fun onPlusClick(habit: Habit)
    fun onMinusClick(habit: Habit)
    fun onTitleClick(habit: Habit)
}
