package com.example.attendalert.app
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendalert.data.UserInformationViewModel
import com.example.attendalert.Navigation.Screen
import com.example.attendalert.data.CLassInformationViewModel
import com.example.attendalert.screens.GoogleSignIn
import com.example.attendalert.screens.ScheduleUi

//import com.example.attendalert.screens.InsertButton


//@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendAlertApp() {
    val viewModel: UserInformationViewModel = viewModel()
    val viewModelc:CLassInformationViewModel=viewModel()
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        NavHost(navController = navController, startDestination = Screen.GoogleSignIn.route) {
            composable(route = Screen.GoogleSignIn.route) {
                GoogleSignIn(navController = navController,viewModel = viewModel)
            }
            composable(route = Screen.Home.route) {
                ScheduleUi(modifier = Modifier,viewModel = viewModel,viewModelc=viewModelc)
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAK