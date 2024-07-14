package com.example.attendalert.app
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendalert.screens.GoogleSignIn
import com.example.attendalert.screens.InsertButton


//@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AttendAlertApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
//        InsertButton()
        GoogleSignIn()
    }
}

//@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Preview
@Composable
private fun prevv() {
    AttendAlertApp()
}