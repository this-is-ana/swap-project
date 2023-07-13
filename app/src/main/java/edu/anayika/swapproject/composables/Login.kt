package edu.anayika.swapproject.composables

import android.content.Context
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.google.firebase.auth.FirebaseUser
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.Authentication
import edu.anayika.swapproject.utils.ClickOutsideToDismissKeyboard
import edu.anayika.swapproject.utils.isValidEmail
import edu.anayika.swapproject.utils.isValidPassword
import edu.anayika.swapproject.utils.showErrorMessage


@Composable
fun Login(navController: NavController) {
    LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(navController = navController, showBackButton = false, currentActivity = CurrentActivity.MainActivity)
        LoginForm(navController = navController)
    }
}
@OptIn(ExperimentalComposeUiApi::class)
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
                style = typography.h5,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Start),
            )
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )}
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )}
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        val currentUser = Authentication().signIn(email.value, password.value)
                        validateLoginInput(currentUser, context, navController, email, password)
                    }
                        ) {
                    Text(
                        text = "Log in",
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = typography.h6
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
                color = MaterialTheme.colorScheme.tertiary,
                style = typography.h6,
                modifier = Modifier.clickable { /*resetPassword()*/ }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Create a new account")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
                style = typography.h6,
                modifier = Modifier.clickable { navController.navigate("createAccount") }
            )
        }
    }
}

fun validateLoginInput(
    currentUser: FirebaseUser?,
    context: Context,
    navController: NavController,
    email: MutableState<String>,
    password: MutableState<String>
) {
    var errMsg = "Courriel ou mot de passe invalide"
    if (currentUser != null) {
        if (isValidEmail(email.value, navController, context)) {
            if (isValidPassword(password.value)) {
    //            if (password.value == currentUser.password) {
                    navController.navigate("userSession")
    //            } else {
    //                showErrorMessage(errMsg, context)
    //            }
            } else {
                errMsg = "Mot de passe doit avoir 6 characters"
                showErrorMessage(errMsg, context)
            }
        } else {
             errMsg = "Le courriel n'est pas valide"
            showErrorMessage(errMsg, context)
        }
    } else {
        showErrorMessage(errMsg, context)
    }
}


@Preview(name = "Login")
@Composable
private fun PreviewLogin() {
    Login(navController = rememberNavController())
}