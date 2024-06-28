package com.example.attendalert.data

sealed class UIEvent {
    data class NameChanged(val name: String) : UIEvent()
    data class EmailChanged(val email: String) : UIEvent()
    data class PasswordChanged(val password: String) : UIEvent()
    data class SemesterChanged(val semester: Int) : UIEvent()
    object registerButtonClicked : UIEvent()
}