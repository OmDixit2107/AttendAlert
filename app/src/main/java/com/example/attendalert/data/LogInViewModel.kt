package com.example.attendalert.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LogInViewModel : ViewModel() {
    var registrationUIState = mutableStateOf(RegistrationUIState())
    fun onEvent(event: UIEvent) {
    when (event) {
        is UIEvent.NameChanged -> {
            registrationUIState.value = registrationUIState.value.copy(name = event.name)
            printstate()
        }
        is UIEvent.EmailChanged -> {
    registrationUIState.value = registrationUIState.value.copy(email = event.email)
            printstate()
    }
        is UIEvent.PasswordChanged -> {
            registrationUIState.value = registrationUIState.value.copy(password = event.password)
            printstate()
        }
        is UIEvent.SemesterChanged->{
            registrationUIState.value = registrationUIState.value.copy(semester = event.semester)
            printstate()
        }
        is UIEvent.registerButtonClicked->{
            signUp()
        }
    }
    }

    private fun signUp() {
        println("U JUST SIGNED UP")
    }

    private fun printstate(){
        Log.d("TAG", "onEvent: ${registrationUIState.value}")
    }
}