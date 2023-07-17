package edu.anayika.swapproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.activities.ui.theme.SwapProjectTheme
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.composables.UpdateChaletView
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.UserProfileViewModel

class UpdateChaletActivity : AppCompatActivity() {
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
fun UpdateChalet(navController: NavController, houseId: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            navController = navController,
            showBackButton = true,
            currentActivity = CurrentActivity.SearchChaletsActivity
        )

        UpdateChaletView(navController, houseId)
    }
}