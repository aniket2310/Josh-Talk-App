package com.example.joshtalk.data

data class TaskHistoryItem(
    val id: String,
    val type: TaskType,
    val durationSec: Int,
    val timestamp: String,
    val previewText: String? = null,
    val hasImage: Boolean = false
)
