package com.example.joshtalk

import androidx.compose.ui.window.ComposeUIViewController

import com.example.joshtalk.navigation.Navigation
import androidx.compose.ui.interop.LocalUIViewController
import com.example.joshtalk.platform.JoshContext


fun MainViewController() = ComposeUIViewController(

){

    val viewController = LocalUIViewController.current

    Navigation(
        joshContext = JoshContext(viewController)
    )
}