package edu.anayika.swapproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.activities.ui.theme.SwapProjectTheme
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.UserProfileViewModel

class SearchChaletsActivity : ComponentActivity() {
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
fun SearchChalets(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            navController = navController,
            showBackButton = true,
            currentActivity = CurrentActivity.SearchChaletsActivity
        )
        // SearchChaletView(navController)
    }
}



@Preview(showBackground = true)
@Composable
fun SearchChaletsActivityPreview(navController: NavController = rememberNavController()) {
    SwapProjectTheme {
        SearchChalets(navController)

    }
}