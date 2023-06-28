package edu.anayika.swapproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
                CreateAccount(navController = navController)
                AppNavHost(navController = navController)

            }
        }
    }
}
@Composable
fun CreateAccount(navController: NavController) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(navController = navController, showBackButton = true)
        NewAccountForm()

    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountPreview(navController: NavController = rememberNavController()) {
    SwapProject_Theme {
        CreateAccount(navController)
    }
}
