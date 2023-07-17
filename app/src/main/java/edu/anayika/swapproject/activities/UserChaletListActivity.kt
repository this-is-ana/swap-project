package edu.anayika.swapproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.activities.ui.theme.SwapProjectTheme
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.composables.UserChaletListView
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.UserProfileViewModel

class UserChaletListActivity : ComponentActivity() {
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
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            navController = navController,
            showBackButton = true,
            currentActivity = CurrentActivity.userChaletListActivity
        )

        Text(
            text = "Chalets",
            style = typography.h5,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(12.dp))

        UserChaletListView(navController)
    }
}



@Preview(showBackground = true)
@Composable
fun AccountChaletListPreview() {
    SwapProjectTheme {
        AccountChaletList(navController = rememberNavController())
    }
}