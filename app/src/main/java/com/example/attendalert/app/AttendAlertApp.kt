package com.example.attendalert.app
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.attendalert.screens.SignUpScreen

@Composable
fun AttendAlertApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        SignUpScreen()
    }
}