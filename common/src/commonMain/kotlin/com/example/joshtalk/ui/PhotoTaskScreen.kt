package com.example.joshtalk.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.joshtalk.audioPermission.AudioPermission
import com.example.joshtalk.components.AppIcons
import com.example.joshtalk.components.Gauge
import com.example.joshtalk.navigation.Screens
import com.example.joshtalk.platform.JoshContext
import com.example.joshtalk.platform.rememberCameraLauncher
import com.example.joshtalk.viewModel.PhotoTaskViewModel
import com.example.joshtalk.viewModel.TextTaskViewModel
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoTaskScreen(
    navCtrl : NavHostController,
    joshContext: JoshContext,
    viewModel: PhotoTaskViewModel = koinInject() ){



    val capturedImage = viewModel.capturedImage.value

    val isRecording = viewModel.isRecording.value
    val duration = viewModel.recordingDuration.value
    val errorMessage = viewModel.errorMessage.value
    val isRecordingSuccess = viewModel.isRecordingFinished.value

    val isPlaying = viewModel.isPlaying.value

    val currentProgress = viewModel.playbackProgress.value

    val cameraLauncher = rememberCameraLauncher { bitmap ->
        viewModel.onImageCaptured(bitmap)
    }


    val animatedProgress by animateFloatAsState(
        targetValue = if (isRecordingSuccess && !isPlaying && currentProgress == 0f) 1f else currentProgress,
        label = "progress"
    )


    val audioPermission = remember { AudioPermission(joshContext) }
    audioPermission.Bind()
    var hasPermission by remember { mutableStateOf(audioPermission.isGranted()) }

    LaunchedEffect(Unit) {
        if (!hasPermission) audioPermission.request { hasPermission = it }
    }

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
                            text = "Recording Task",
                            color = Color.White

                        )
                    }
                },


                actions = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { }) {
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
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            Text(
                text = "Instruction: Capture an image and describe in your native language.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(16.dp))


            if (capturedImage == null) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray.copy(0.3f))
                        .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Button(
                            onClick = { cameraLauncher.launch() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE).copy(0.7f))
                        ) {
                            Text("Capture Image")
                        }
                    }
                }
            } else {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Image(
                        bitmap = capturedImage,
                        contentDescription = "Captured",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    IconButton(
                        onClick = { viewModel.retakePhoto() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(Color.Black.copy(0.5f), CircleShape)
                    ) {
                        Icon(Icons.Default.Refresh, "Retake", tint = Color.White)
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

                if (errorMessage != null) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Duration: ${duration}s",
                        color = if (isRecording) Color.Red else Color.Black,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isRecordingSuccess) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(60.dp)
                                .scale(if (isRecording) 1.2f else 1.0f)
                                .background(
                                    color = if (isRecording) Color(0xFF6200EE).copy(0.4f) else Color(
                                        0xFF6200EE
                                    ).copy(0.8f),
                                    shape = CircleShape
                                )
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            if (hasPermission) {
                                                viewModel.startRecording()
                                                tryAwaitRelease()
                                                viewModel.stopRecording()
                                            } else {
                                                audioPermission.request { hasPermission = it }
                                            }
                                        }
                                    )
                                }
                        ) {
                            Icon(
                                imageVector = AppIcons.Mic,
                                contentDescription = "Record",
                                tint = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        Text(
                            text = if (isRecording) "Recording..." else "Press and Hold to Record",
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }


                if (isRecordingSuccess) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { viewModel.togglePlayback() }) {
                            Icon(
                                if (isPlaying) Icons.Default.MoreVert else Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = Color(0xFF6200EE))
                        }
                        LinearProgressIndicator(
                            progress = { if (isPlaying) animatedProgress else 1f },
                            modifier = Modifier.weight(1f).height(6.dp),
                            color = Color(0xFF6200EE),
                            trackColor = Color.LightGray
                        )
                        IconButton(onClick = { viewModel.resetRecording() }) {
                            Icon(Icons.Default.Refresh, "Redo", tint = Color.Gray)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Checkboxes
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Text(text = "Before Submiting listen audio and check these or else Rerecord the passage ")

                        Spacer(modifier = Modifier.height(12.dp))

                        CheckboxRow("No background noise", viewModel.checkboxNoise)
                        CheckboxRow("No mistakes while reading", viewModel.checkboxMistakes)
                        CheckboxRow("Beech me koi galti nahi hai", viewModel.checkboxGalti)
                    }

                    Spacer(modifier = Modifier.height(24.dp))


                    Button(
                        onClick = {
                            viewModel.submitTask()

                            navCtrl.popBackStack()
                        },
                        enabled = viewModel.canSubmit(),
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6200EE).copy(0.7f),
                            disabledContainerColor = Color.Gray
                        )
                    ) {
                        Text("Submit Task")
                    }

                    TextButton(
                        onClick = { viewModel.resetRecording() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Record Again", color = Color.Gray)
                    }
                }

        }
    }
}