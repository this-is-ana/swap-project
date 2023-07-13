package edu.anayika.swapproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.composables.NewChaletForm
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.UserProfileViewModel
import edu.anayika.swapproject.ui.theme.SwapProjectTheme

class NewChaletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SwapProjectTheme {
            val navController = rememberNavController()
            AppNavHost(navController = navController, viewModel = UserProfileViewModel())
            }

        }
    }
}
@Composable
fun AddNewChalet(navController: NavController) {
    val context = LocalContext.current
    AppTopBar(navController = navController, showBackButton = true, currentActivity = CurrentActivity.NewChaletActivity)
    NewChaletForm(navController)
}


@Preview(showBackground = true)
@Composable
fun AddNewChaletPreview(navController: NavController = rememberNavController()) {
    SwapProjectTheme {
        AddNewChalet(navController)
    }
}