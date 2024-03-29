package edu.anayika.swapproject.composables

import android.content.Context

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.User
import edu.anayika.swapproject.data.UserType

import edu.anayika.swapproject.utils.isValidEmail
import edu.anayika.swapproject.utils.isValidPassword
import edu.anayika.swapproject.utils.showErrorMessage

@Composable
fun NewAccountForm(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    var errMsg: String
    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            item {
                Column(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Nouveau compte",
                        style = typography.h5,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Start),
                    )
                    TextFieldWithLabel(
                        label = "Courriel",
                        value = email.value,
                        onValueChange = { email.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PasswordTextFieldWithLabel(
                        label = "Mot de passe",
                        value = password.value,
                        onValueChange = { password.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PasswordTextFieldWithLabel(
                        label = "Confirmer mot de passe",
                        value = passwordConfirmation.value,
                        onValueChange = { passwordConfirmation.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Prénom",
                        value = firstName.value,
                        onValueChange = { firstName.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Nom de famille",
                        value = lastName.value,
                        onValueChange = { lastName.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Téléphone",
                        value = phone.value,
                        onValueChange = { phone.value = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            if(password.value == passwordConfirmation.value){
                                val userInputs = User(
                                    email.value,
                                    firstName.value,
                                    lastName.value,
                                    phone.value,
                                    UserType.REGULAR
                                )

                                createUser(userInputs, navController, context, password.value)
                            } else {
                                errMsg = "Les mots de passe ne sont pas correpondants"
                                showErrorMessage(errMsg, context)
                            }
                        },

                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Create Account",
                            style = typography.h6)
                    }
                }
            }
        }
    }
}

fun createUser(userInputs: User, navController: NavController, context: Context, password: String) {
    if(isValidEmail(userInputs.email) && isValidPassword(password)) {
        DatabaseHelper().createUser(userInputs, password, navController, context)
    } else {
        val errMsg = "Les mots de passe ne sont pas correpondants"
        showErrorMessage(errMsg, context)
    }
}


@Preview(name = "NewAccountForm")
@Composable
private fun NewAccountFormPreview() {
    NewAccountForm(navController = rememberNavController())
}