package com.example.attendalert.Navigation

sealed class Screen(val route: String) {
    object GoogleSignIn : Screen("google_sign_in")
    object Home : Screen("home")
}
