package edu.anayika.swapproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.ui.theme.SwapProjectTheme

class UserSessionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapProjectTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
@Composable
fun UserSession(navController: NavController) {
        val context = LocalContext.current
        Column(modifier = Modifier.fillMaxSize()) {
            AppTopBar(navController = navController, showBackButton = false, showAddChalet = true )
            UserSessionView(navController = navController)
        }
}


@Preview(showBackground = true)
@Composable
fun ShowUserSessionPreview(navController: NavController = rememberNavController()) {
    SwapProjectTheme {
        UserSession(navController)
    }
}