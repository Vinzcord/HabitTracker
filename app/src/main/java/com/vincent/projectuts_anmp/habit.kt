package com.vincent.projectuts_anmp

data class Habit(
    val name: String,
    val description: String,
    val goal: Int,
    val unit: String,
    val icon: Int,
    var currentProgress: Int = 0
) {
}