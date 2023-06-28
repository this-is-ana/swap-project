package edu.anayika.swapproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import edu.anayika.swapproject.theme.SwapProject_Theme

class CreateAccountActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapProject_Theme {
                val navController = rememberNavController()
                AppTopBar(navController = navController, showBackButton = true)
                CreateAccountPage(navController)
            }
        }
    }
}

@Composable
fun CreateAccountPage(navController: NavController) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(navController = navController, showBackButton = false)
        Spacer(modifier = Modifier.height(16.dp))
        AccountSection()
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle create account logic here */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Create Account")
        }
    }
}

@Composable
fun AccountSection() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        TextFieldWithLabel(label = "Email", value = email.value, onValueChange = { email.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithLabel(label = "Password", value = password.value, onValueChange = { password.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithLabel(
            label = "Confirm Password",
            value = passwordConfirmation.value,
            onValueChange = { passwordConfirmation.value = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithLabel(label = "First Name", value = firstName.value, onValueChange = { firstName.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithLabel(label = "Last Name", value = lastName.value, onValueChange = { lastName.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithLabel(label = "Address", value = address.value, onValueChange = { address.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithLabel(label = "Phone", value = phone.value, onValueChange = { phone.value = it })
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
                .padding(8.dp),
            label = { Text(text = label) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountPreview(navController: NavController = rememberNavController()) {
    SwapProject_Theme {
        CreateAccountPage(navController)
    }
}