package com.example.attendalert.components

import BgColor
import GrayColor
import Primary
import Secondary
import TextColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalTextComponent(value : String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.White,
        textAlign = TextAlign.Center
    )
}
@Composable
fun HeadingTextComponent(value : String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ClickableTextComponent (value : String,onClick : () -> Unit ) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .clickable { onClick() },
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DividerTextComp(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = GrayColor
        )
        Text(text = "OR", fontSize = 20.sp, color = TextColor)
        Divider(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = GrayColor
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(modifier: Modifier = Modifier, label: String, ic: ImageVector,
                onTextChanged: (String) -> Unit) {
    val textValue = remember { mutableStateOf("") } // Renamed for clarity

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)), // Use the provided modifier
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        value = textValue.value, // Bind the textValue state
        onValueChange = { newValue ->
            textValue.value = newValue
            onTextChanged(newValue)
        },
        leadingIcon = {
            Icon(imageVector = (ic), contentDescription = "name")
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNumberField(modifier: Modifier = Modifier, label: String, ic: ImageVector,
                  onTextChanged: (String) -> Unit) {
    val textValue = remember { mutableStateOf("") } // Renamed for clarity

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)), // Use the provided modifier
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.NumberPassword),
        singleLine = true,
        value = textValue.value, // Bind the textValue state
        onValueChange = { newValue ->
            textValue.value = newValue
            onTextChanged(newValue)
        },
        leadingIcon = {
            Icon(imageVector = (ic), contentDescription = "name")
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(modifier: Modifier = Modifier, label: String, ic: ImageVector,
                      onTextChanged: (String) -> Unit) {
    val password = remember { mutableStateOf("") }
    val passVisible = remember { mutableStateOf(false) } // Renamed for clarity

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)),
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        value = password.value,
        onValueChange = { newValue ->
            password.value = newValue
            onTextChanged(newValue)
        },
        leadingIcon = {
            Icon(imageVector = ic, contentDescription = "leading icon") // More descriptive
        },
        trailingIcon = {
            val iconImage = if (passVisible.value) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(onClick = { passVisible.value = !passVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = "toggle password visibility")
            }
        },
        visualTransformation = if (passVisible.value) VisualTransformation.None else PasswordVisualTransformation() // Add visual transformation
    )
}

@Composable
fun ButtonComponent(value: String,onButtonClicked: () -> Unit) {
    Button(
        onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Primary, Secondary)),
                    shape = RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
            ){
            Text(
                text = value,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }
}