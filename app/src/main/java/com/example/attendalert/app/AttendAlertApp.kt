package com.example.attendalert.app
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
import com.example.attendalert.screens.LogInScreen
import com.example.attendalert.screens.SignUpScreen

@Composable
fun AttendAlertApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        val navcontroller = rememberNavController()
        NavHost(navController = navcontroller, startDestination = "SignUpScreen") {
            composable(route = "SignUpScreen"){
                SignUpScreen({ navcontroller.navigate("SignInScreen") }, viewModel())
            }
            composable(route = "SignInScreen"){
                LogInScreen{
                    navcontroller.navigate("SignUpScreen")
                }
            }
        }
    }
}

@Preview
@Composable
private fun prevv() {
    AttendAlertApp()
}