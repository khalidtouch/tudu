package com.reel.tudu.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CompletedTaskWithTodoItems(
    @Embedded val completedTask: CompletedTask,
    @Relation(
        parentColumn = "completionDay",
        entityColumn = "completionDay"
    )
    val todoItems: List<TodoItem>
)