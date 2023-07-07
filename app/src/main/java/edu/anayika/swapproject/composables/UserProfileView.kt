package edu.anayika.swapproject.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.models.UserProfileViewModel

@Composable
fun UserProfileView(
    navController: NavController,
    viewModel: UserProfileViewModel,
    modifier: Modifier = Modifier
) {
    val email = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val userAddress = remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        Column(modifier = Modifier.fillMaxHeight()) {
                val userProfile by viewModel.userProfile.collectAsState()
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()
                ) {
                    Text(text = "Name: John Doe")
                    //REPLACE: Text(text = "Name: ${user.firstName} ${user.lastName}")
                    Text(text = "Email: example@example.com")//REPLACE: Text(text = "Email: ${user.email}")
                    Text(text = "Phone: 444-444-4444")//REPLACE: Text(text = "Phone: ${user.phone}")
                    Text(text = "User Type: PREMIUM")//REPLACE: Text(text = "User Type: ${user.userType}")
                    //REPLACE: Text(text = "Address: ${it.address1}, ${it.address2}")
                    Text(text = "City: Montreal, Province: Quebec")
                    //REPLACE: Text(text = "City: ${it.city}, Province: ${it.province}")
                    Text(text = "Postal Code: A0A 0A0, Country: Canada")
                    //REPLACE: Text(text = "Postal Code: ${it.postalCode}, Country: ${it.country}")

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { navController.navigate("addNewChalet")/* Handle login logic here */ }
                    ) {
                        Text(
                            text = "Add a new chalet",
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.h6
                        ) }
                }
                    }
                }
            }





@Preview(name = "UserProfileView")
@Composable
private fun PreviewUserProfileView() {
    val viewModel = UserProfileViewModel()
    viewModel.fetchUserProfile()
    UserProfileView(navController = rememberNavController(), viewModel = viewModel)
}