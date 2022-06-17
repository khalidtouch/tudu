package com.reel.tudu.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    @PrimaryKey val itemId: Int,
    val task: String,
    val completionDay: Int? = null,
)

@Entity
data class CompletedTask(
    @PrimaryKey val completionDay: Int,
)