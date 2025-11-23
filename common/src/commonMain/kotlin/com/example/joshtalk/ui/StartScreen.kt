package com.example.joshtalk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
fun StartScreen(navCtrl: NavHostController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                        Text(
                            "Josh Talk",
                            color = Color.White,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE).copy(0.7f),
                    titleContentColor = Color.White
                ),

                modifier = Modifier.height(80.dp)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Lets Start with a",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,


            )
            Text(
                text = "Simple Task",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFF6200EE).copy(0.7f)

            )
            Text(
                text = "for practice",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.padding(vertical = 20.dp))

            Text(
                text = "Phele hum ek",
            )

            Text(
                text = "sample task karte hain",
            )


            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                navCtrl.navigate(Screens.NoiseTestScreen.route)
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
                    text = "Start Sample Task",
                )

            }
        }
    }
}