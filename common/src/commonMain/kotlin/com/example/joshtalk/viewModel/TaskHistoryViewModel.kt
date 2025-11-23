package com.example.joshtalk.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.joshtalk.data.TaskHistoryItem
import com.example.joshtalk.data.TaskType
import com.example.joshtalk.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class TaskHistoryViewModel(
    private val repository: TaskRepository
){

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _taskList = mutableStateOf<List<TaskHistoryItem>>(emptyList())
    val taskList: State<List<TaskHistoryItem>> = _taskList

    private val _totalTasks = mutableStateOf(0)
    val totalTasks: State<Int> = _totalTasks

    private val _totalDuration = mutableStateOf("0s")
    val totalDuration: State<String> = _totalDuration

    init {
        loadTasks()
    }

    private fun loadTasks() {
        scope.launch {
            try {

                repository.getAllTasks().collect { tasks ->
                    _taskList.value = tasks
                    _totalTasks.value = tasks.size
                    val totalSecs = tasks.sumOf { it.durationSec }
                    _totalDuration.value = formatDuration(totalSecs)
                    println("Tasks loaded: ${tasks.size} tasks")
                }
            } catch (e: Exception) {
                println("Error loading tasks: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    private fun formatDuration(totalSeconds: Int): String {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return if (minutes > 0) "${minutes}m ${seconds}s" else "${seconds}s"
    }
} 