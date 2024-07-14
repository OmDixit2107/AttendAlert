package com.example.attendalert.screens
import android.content.ContentValues.TAG
import androidx.credentials.GetCredentialRequest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.credentials.CredentialManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attendalert.R
import com.example.attendalert.components.HeadingTextComponent
import com.example.attendalert.components.NormalTextComponent
import com.example.attendalert.supabase
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID


//@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun GoogleSignIn (){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.png_gradient),
            contentDescription = "Google Logo",
            modifier = Modifier
                .padding(end = 8.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))
        NormalTextComponent(value = "Hey There")
        HeadingTextComponent(value = "Sign Into Your Account!")
//            InsertButton()
        GoogleSignInButton()
    }
}

//@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun GoogleSignInButton() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val onClick: () -> Unit = {
        val credentialManager = CredentialManager.create(context)
        val rawNonce = UUID.randomUUID().toString() // Generate a random String. UUID should be sufficient, but can also be any other random string.
        val bytes = rawNonce.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) } // Hashed nonce to be passed to Google sign-in


        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("375845635297-pje1hi3mr2mi91iamst3aji1jq9j00np.apps.googleusercontent.com")
            .setNonce(hashedNonce) // Provide the nonce if you have one
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )

                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(result.credential.data)

                val googleIdToken = googleIdTokenCredential.idToken
                val email =googleIdTokenCredential.id
                // Check if the email is from iiitvadodara.ac.in domain
                if (email.endsWith("@iiitvadodara.ac.in")) {
                    // Proceed with sign-in logic for valid domain
                    supabase.auth.signInWith(IDToken){
                        idToken=googleIdToken
                        provider= Google
                        nonce=rawNonce
                    }
                    Log.i(TAG, googleIdToken)
                    Toast.makeText(context, "Sign-in successful", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(context, "Sign-in successful", Toast.LENGTH_SHORT).show()
                } else {
                    // Show toast for invalid domain
                    Toast.makeText(context, "Sign-in restricted to @iiitvadodara.ac.in emails", Toast.LENGTH_SHORT).show()
                }
                // Handle successful sign-in

            } catch (e: androidx.credentials.exceptions.GetCredentialException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    GoogleSignInButton(onClick = onClick)
}


@Composable
fun GoogleSignInButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_google_logo_48),
                contentDescription = "Google Logo",
                modifier = Modifier
                    .padding(end = 8.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Sign in with Google",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}