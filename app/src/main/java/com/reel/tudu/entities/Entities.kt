package com.reel.tudu.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val itemId: Int = 0,
    val task: String,
    var isCompleted: Int? = 0,
)
