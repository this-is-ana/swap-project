package edu.anayika.swapproject.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.activities.ui.theme.SwapProjectTheme
import edu.anayika.swapproject.composables.AppNavHost
import edu.anayika.swapproject.composables.AppTopBar
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.models.UserProfileViewModel

class ContactUsActivity : ComponentActivity() {
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
fun ContactUs(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            navController = navController,
            showBackButton = true,
            currentActivity = CurrentActivity.ContactUsActivity
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier

                    .padding(top = 16.dp, bottom = 16.dp),) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Contactez-nous!", style = typography.h6)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        SendEmailButton()
                    }
                }
            }
        }
    }
}



@Composable
fun SendEmailButton() {
    val context = LocalContext.current
    val emailLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result here if needed
    }

    Button(onClick = { sendEmail(context, emailLauncher) }) {
        Text(text = "Envoyer un courriel")
    }
}

private fun sendEmail(context: Context, emailLauncher: ActivityResultLauncher<Intent>) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // Only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, arrayOf("clientservices@example.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Regarding App Inquiry")
        putExtra(Intent.EXTRA_TEXT, "Hello, I have a question about your app.")
    }

    emailLauncher.launch(emailIntent)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SwapProjectTheme {
        ContactUs(navController = rememberNavController())
    }
}