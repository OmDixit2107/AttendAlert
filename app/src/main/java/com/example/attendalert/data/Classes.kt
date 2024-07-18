package com.example.attendalert.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendalert.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
//import io.supabase.supabasekt.postgrest.PostgrestFilterBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ClassSchedule(val class1: String, val class2: String)

class CLassInformationViewModel : ViewModel() {
    private val _classSchedule = MutableStateFlow<ClassSchedule?>(null)
    val classSchedule: StateFlow<ClassSchedule?> = _classSchedule

    fun fetchClassSchedule(day: String, semester: Int, branch: String) {
        viewModelScope.launch {
            try {
                // Fetch Class1 and Class2 from Supabase
                val class1Response = supabase.from("Classes").select(columns = Columns.list("class1")) {
                    filter {
                        eq("day", day)
                        eq("semester", semester)
                        eq("branch", branch)
                    }
                }

                val class2Response = supabase.from("Classes").select(columns = Columns.raw("class2")) {
                    filter {
                        eq("day", day)
                        eq("semester", semester)
                        eq("branch", branch)
                    }
                }

                // Update _classSchedule with the fetched data
                val class1 = class1Response.data
                val class2 = class2Response.data
                _classSchedule.value = ClassSchedule(class1, class2)
            } catch (e: Exception) {
                // Handle error (e.g., log, show error message, etc.)
                _classSchedule.value = null // Reset state or handle appropriately
            }
        }
    }
}