package com.example.joshtalk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.joshtalk.platform.JoshContext
import com.example.joshtalk.ui.ImageTaskScreen
import com.example.joshtalk.ui.NoiseTestScreen
import com.example.joshtalk.ui.PhotoTaskScreen
import com.example.joshtalk.ui.StartScreen
import com.example.joshtalk.ui.TaskHistoryScreen
import com.example.joshtalk.ui.TaskSelectionScreen
import com.example.joshtalk.ui.TextTaskScreen

@Composable
fun Navigation(joshContext: JoshContext){
    val navCtrl = rememberNavController()

    NavHost(
        navController = navCtrl,
        startDestination = Screens.StartScreen.route
    ){

        composable(Screens.StartScreen.route){
            StartScreen(navCtrl)
        }

        composable(Screens.NoiseTestScreen.route){
            NoiseTestScreen(navCtrl,joshContext)
        }

        composable(Screens.TaskSelectionScreen.route){
            TaskSelectionScreen(navCtrl)
        }

        composable(Screens.ImageTaskScreen.route){
            ImageTaskScreen(navCtrl,joshContext)
        }

        composable(Screens.PhotoTaskScreen.route){
            PhotoTaskScreen(navCtrl,joshContext)
        }

        composable(Screens.TextTaskScreen.route){
            TextTaskScreen(navCtrl,joshContext)
        }

        composable(Screens.TaskHistoryScreen.route){
            TaskHistoryScreen(navCtrl)
        }

    }
}