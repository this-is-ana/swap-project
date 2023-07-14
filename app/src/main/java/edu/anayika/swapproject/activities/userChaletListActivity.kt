package edu.anayika.swapproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.activities.ui.theme.SwapProjectTheme
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.composables.NewChaletForm
import edu.anayika.swapproject.composables.UserChaletListView
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.UserProfileViewModel

class userChaletListActivity : ComponentActivity() {
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
fun AccountChaletList(navController: NavController) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            navController = navController,
            showBackButton = true,
            currentActivity = CurrentActivity.userChaletListActivity
        )
        UserChaletListView(navController)
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwapProjectTheme {
        Greeting("Android")
    }
}