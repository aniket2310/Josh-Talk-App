package com.example.joshtalk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.joshtalk.audioPermission.AudioPermission
import com.example.joshtalk.audioSensor.AudioSensor
import com.example.joshtalk.components.Gauge
import com.example.joshtalk.navigation.Screens
import com.example.joshtalk.platform.JoshContext
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoiseTestScreen(
    navCtrl:NavHostController,
    joshContext: JoshContext
    ){

    val sensor = remember { AudioSensor() }
    val audioPermission = remember { AudioPermission(joshContext) }

    audioPermission.Bind()

    var hasPermission by remember { mutableStateOf(audioPermission.isGranted()) }
    var noiseDb by remember { mutableStateOf(0f) }
  //  var showPermissionDialog by remember { mutableStateOf(!hasPermission) }



    LaunchedEffect(Unit) {
        if (!hasPermission) {
            audioPermission.request { granted ->
                hasPermission = granted
            }
        }
    }

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            sensor.start()
            while (true) {
                noiseDb = sensor.getNoiseLevel()
                delay(200)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            sensor.stop()
        }
    }
    val canStart = noiseDb <= 40f && hasPermission
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                modifier = Modifier.height(80.dp),


                navigationIcon = {

                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { navCtrl.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                },


                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sample Task",
                            color = Color.White

                        )
                    }
                },


                actions = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = {  }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6200EE).copy(0.7f),
                    titleContentColor = Color.White
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
        ) {

            Text(
                text = "Test Ambient Noise Level",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Before you can start the call we will have to check your ambient noise level",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.padding(vertical = 24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Gauge(
                    value = noiseDb,
                    min = 0f,
                    max = 100f
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 24.dp))

            Button(onClick = {
                navCtrl.navigate(Screens.TaskSelectionScreen.route)
            },
                enabled = canStart,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    Color(0xFF6200EE).copy(0.7f),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),

                ){
                Text(
                    text = if (canStart) "Start Test" else "Decrease Noise to Start",
                    color = Color.White
                )

            }
        }
    }
}

