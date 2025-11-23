package com.example.joshtalk.navigation

sealed class Screens(val route: String){
    data object StartScreen : Screens("start_screen")
    data object NoiseTestScreen : Screens("noise_test_screen")
    data object TaskSelectionScreen : Screens("task_selection_screen")
    data object TextTaskScreen : Screens("text_task_screen")
    data object PhotoTaskScreen : Screens("photo_task_screen")
    data object ImageTaskScreen : Screens("image_task_screen")
    data object TaskHistoryScreen : Screens("task_history_screen")
}