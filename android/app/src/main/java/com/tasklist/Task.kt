package com.tasklist

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val completed: Boolean = false
)
