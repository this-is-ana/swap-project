package edu.anayika.swapproject.composables

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.models.Authentication

@Composable
fun Login(navController: NavController) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(navController = navController, showBackButton = false)
        LoginForm(navController = navController)
    }
}
@Composable
fun LoginForm(navController: NavController) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Sign in",
                style = MaterialTheme.typography.h5,
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Start),
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary),
                    onClick = {
                        val currentUser = Authentication().signIn(email.value, password.value)
                        val errorMessage = Toast.makeText(
                            context,
                            "Courriel ou mot de passe invalide",
                            Toast.LENGTH_SHORT)

                        if (currentUser != null) {
                            if(currentUser.email == email.value) {
                                navController.navigate("userSession")
                            } else {
                                errorMessage.show()
                            }
                        } else {
                            errorMessage.show()
                        }
                    }
                        ) {
                    Text(
                        text = "Log in",
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            textDecoration = TextDecoration.Underline,
                            fontSize = 18.sp
                        )
                    ) {
                        append("Reset password")
                    }
                },
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable { /*resetPassword()*/ }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Create a new account")
                    }
                },
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable { navController.navigate("createAccount") }
            )
        }
    }
}

@Preview(name = "Login")
@Composable
private fun PreviewLogin() {
    Login(navController = rememberNavController())
}