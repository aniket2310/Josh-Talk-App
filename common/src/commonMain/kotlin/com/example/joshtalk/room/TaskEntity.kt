package com.example.joshtalk.room


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joshtalk.data.TaskType

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val durationSec: Int,
    val timestamp: String,
    val audioPath: String,


    val textContent: String? = null,
    val imagePath: String? = null
)


fun TaskEntity.toHistoryItem(): com.example.joshtalk.data.TaskHistoryItem {
    return com.example.joshtalk.data.TaskHistoryItem(
        id = this.id.toString(),
        type = com.example.joshtalk.data.TaskType.valueOf(this.type),
        durationSec = this.durationSec,
        timestamp = this.timestamp,
        previewText = this.textContent,
        hasImage = this.imagePath != null
    )
}