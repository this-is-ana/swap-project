package edu.anayika.swapproject.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
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
import edu.anayika.swapproject.models.Authentication

@Composable
fun NewAccountForm(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            item {
                Column(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "New Account",
                        style = MaterialTheme.typography.h5,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Start),
                    )
                    TextFieldWithLabel(
                        label = "Email",
                        value = email.value,
                        onValueChange = { email.value = it })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Password",
                        value = password.value,
                        onValueChange = { password.value = it })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Confirm Password",
                        value = passwordConfirmation.value,
                        onValueChange = { passwordConfirmation.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "First Name",
                        value = firstName.value,
                        onValueChange = { firstName.value = it })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Last Name",
                        value = lastName.value,
                        onValueChange = { lastName.value = it })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithLabel(
                        label = "Phone",
                        value = phone.value,
                        onValueChange = { phone.value = it })
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            val user = User(
                                email.value,
                                password.value,
                                firstName.value,
                                lastName.value,
                                phone.value,
                                UserType.REGULAR)

                            DatabaseHelper().createUser(user)

                            Authentication().createAccount(email.value, password.value)

                            navController.navigate("userProfileAccount")
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Create Account")
                    }
                }
            }
        }
    }
}


@Composable
fun TextFieldWithLabel(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label)
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            label = { Text(text = label) }
        )
    }
}

@Preview(name = "NewAccountForm(")
@Composable
private fun NewAccountFormPreview() {
    NewAccountForm(navController = rememberNavController())
}