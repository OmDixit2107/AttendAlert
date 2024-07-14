package com.example.attendalert

import android.net.http.HttpResponseCache.install
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.attendalert.app.AttendAlertApp
import com.example.attendalert.ui.theme.AttendAlertTheme
import com.google.android.gms.auth.api.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://zgoitnwsbvpypfbsmopq.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpnb2l0bndzYnZweXBmYnNtb3BxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjA3NzE4ODQsImV4cCI6MjAzNjM0Nzg4NH0.boq_Ld94se2IWCXfya5q7p1LMdykjs1P9bU4_NffteU"
) {
    install(io.github.jan.supabase.gotrue.Auth)
    install(Postgrest)
    //install other modules
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendAlertApp()
        }
    }
}

