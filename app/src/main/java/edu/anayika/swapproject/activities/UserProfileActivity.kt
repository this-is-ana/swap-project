package edu.anayika.swapproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import edu.anayika.swapproject.models.UserProfileViewModel
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.composables.UserProfileView
import edu.anayika.swapproject.ui.theme.SwapProjectTheme
import edu.anayika.swapproject.data.CurrentActivity

class UserProfileActivity : ComponentActivity() {
    private val viewModel = UserProfileViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapProjectTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController, viewModel = viewModel)
            }
        }
    }
}
@Composable
fun UserProfileAccount (navController: NavController, viewModel: UserProfileViewModel) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(navController = navController, showBackButton = false, currentActivity = CurrentActivity.UserProfileActivity)
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            item {
                Text(
                    text = "My Profile",
                    style = typography.h5,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Start),
                )
                UserProfileView(navController = navController, viewModel = viewModel)
            }
            //TODO: MyChaletsListView(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileAccountPreview(navController: NavController = rememberNavController()) {
    SwapProjectTheme {
        val viewModel = UserProfileViewModel()
        UserProfileAccount(navController, viewModel)
    }
}