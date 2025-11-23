package com.example.joshtalk.repository


import com.example.joshtalk.room.TaskDao
import com.example.joshtalk.room.TaskEntity
import com.example.joshtalk.room.toHistoryItem
import com.example.joshtalk.data.TaskHistoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime

class TaskRepository(
    private val taskDao: TaskDao
){

    fun getAllTasks(): Flow<List<TaskHistoryItem>> {
        return taskDao.getAllTasks().map { entities ->
            entities.map { it.toHistoryItem() }
        }
    }

    suspend fun getTotalTasks(): Int {
        return taskDao.getCount()
    }

    suspend fun getTotalDuration(): Int {
        return taskDao.getTotalDuration() ?: 0
    }

    suspend fun saveTextReadingTask(
        text: String,
        audioPath: String,
        durationSec: Int
    ) {
        val entity = TaskEntity(
            type = "TEXT_READING",
            durationSec = durationSec,
            timestamp = getCurrentTimestamp(),
            audioPath = audioPath,
            textContent = text
        )
        taskDao.insert(entity)
    }

    suspend fun saveImageDescriptionTask(
        imageUrl: String,
        audioPath: String,
        durationSec: Int
    ) {
        val entity = TaskEntity(
            type = "IMAGE_DESCRIPTION",
            durationSec = durationSec,
            timestamp = getCurrentTimestamp(),
            audioPath = audioPath,
            imagePath = imageUrl
        )
        taskDao.insert(entity)
    }

    suspend fun savePhotoCaptureTask(
        imagePath: String,
        audioPath: String,
        durationSec: Int,
        description: String? = null
    ) {
        val entity = TaskEntity(
            type = "PHOTO_CAPTURE",
            durationSec = durationSec,
            timestamp = getCurrentTimestamp(),
            audioPath = audioPath,
            imagePath = imagePath,
            textContent = description
        )
        taskDao.insert(entity)
    }

    private fun getCurrentTimestamp(): String {

        val now = Clock.System.now()
        val calendar = now.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())

        val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val monthStr = months.getOrNull(calendar.monthNumber - 1) ?: "Jan"
        val dayStr = calendar.dayOfMonth.toString().padStart(2, '0')
        val minStr = calendar.minute.toString().padStart(2, '0')
        val amPm = if (calendar.hour < 12) "AM" else "PM"
        val hour12 = if (calendar.hour == 0) 12 else if (calendar.hour > 12) calendar.hour - 12 else calendar.hour

        return "$monthStr $dayStr, $hour12:$minStr $amPm"
    }

}