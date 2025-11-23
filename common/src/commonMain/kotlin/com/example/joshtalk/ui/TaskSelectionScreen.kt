package com.example.joshtalk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.joshtalk.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSelectionScreen(navCtrl: NavHostController){

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

                // 3. The Title (Centered Vertically and Horizontally)
                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Task Selection",
                            color = Color.White

                        )
                    }
                },


                actions = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /* Handle Menu Click */ }) {
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Button(onClick = {
                navCtrl.navigate(Screens.ImageTaskScreen.route)
            },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    Color(0xFF6200EE).copy(0.7f),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF6200EE).copy(0.7f),
                    disabledContentColor =Color(0xFF6200EE).copy(0.7f)
                ),

                ){
                Text(
                    text = "Start Image Task",
                )

            }

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Button(onClick = {
                navCtrl.navigate(Screens.PhotoTaskScreen.route)
            },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    Color(0xFF6200EE).copy(0.7f),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF6200EE).copy(0.7f),
                    disabledContentColor =Color(0xFF6200EE).copy(0.7f)
                ),

                ){
                Text(
                    text = "Start Photo Task",
                )

            }

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Button(onClick = {
                navCtrl.navigate(Screens.TextTaskScreen.route)
            },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    Color(0xFF6200EE).copy(0.7f),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF6200EE).copy(0.7f),
                    disabledContentColor =Color(0xFF6200EE).copy(0.7f)
                ),

                ){
                Text(
                    text = "Start Text Task",
                )

            }


            Spacer(modifier = Modifier.padding(vertical = 24.dp))

            Button(onClick = {
                navCtrl.navigate(Screens.TaskHistoryScreen.route)
            },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    Color(0xFF6200EE).copy(0.7f),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF6200EE).copy(0.7f),
                    disabledContentColor =Color(0xFF6200EE).copy(0.7f)
                ),

                ){
                Text(
                    text = "Task History",
                )

            }
        }
    }
}