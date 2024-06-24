package com.example.attendalert.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.attendalert.components.ButtonComponent
import com.example.attendalert.components.HeadingTextComponent
import com.example.attendalert.components.MyTextField
import com.example.attendalert.components.NormalTextComponent
import com.example.attendalert.components.PasswordTextField


@Composable
fun LogInScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            NormalTextComponent(value = "Hey There")
            HeadingTextComponent(value = "Welcome Back")
            Spacer(modifier = Modifier.height(40.dp))
            MyTextField(label = "Email", ic = Icons.Default.Email)
//            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(label = "Password", ic = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(value = "LOG IN")
        }

    }
}

@Preview
@Composable
private fun LogInScreenPreview() {
    LogInScreen()
}