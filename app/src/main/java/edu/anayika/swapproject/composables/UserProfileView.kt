package edu.anayika.swapproject.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.User
import edu.anayika.swapproject.data.UserType
import edu.anayika.swapproject.models.Authentication
import edu.anayika.swapproject.models.UserProfileViewModel

@Composable
fun UserProfileView(
    navController: NavController,
    viewModel: UserProfileViewModel,
    modifier: Modifier = Modifier
) {
    val currentUserEmail = Authentication().getCurrentUser()?.email!!
    val email = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val userType = remember { mutableStateOf("") }
    //val userAddress = remember { mutableStateOf("") }
    lateinit var user: User

    Thread.sleep(1000)
    DatabaseHelper().readUserByEmail(currentUserEmail).addOnSuccessListener { results ->
        for (result in results) {
            user = User(
                result.data["email"].toString(),
                result.data["firstName"].toString(),
                result.data["lastName"].toString(),
                result.data["phone"].toString(),
                UserType.REGULAR
            )
            email.value = user.email
            firstName.value = user.firstName
            lastName.value = user.lastName
            phone.value = user.phone
            userType.value = user.userType.toString()
        }
    }
    UserProfileSection(viewModel,user)
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun UserProfileSection(viewModel: UserProfileViewModel, user: User) {

    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        Column(modifier = Modifier.fillMaxHeight()) {
            val userProfile by viewModel.userProfile.collectAsState()
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Nom: ${user.firstName} ${user.lastName}",
                        style = typography.subtitle1
                    )
                    Text(
                        text = "Courriel: ${user.email}",
                        style = typography.subtitle1
                    )
                    Text(text = "Téléphone: ${user.phone}", style = typography.subtitle1)
                    Text(text = "Abonnement: ${user.userType}", style = typography.subtitle1)
                    //REPLACE: Text(text = "Address: ${it.address1}, ${it.address2}")
                    //Text(text = "City: Montreal, Province: Quebec")
                    //REPLACE: Text(text = "City: ${it.city}, Province: ${it.province}")
                    //Text(text = "Postal Code: A0A 0A0, Country: Canada")
                    //REPLACE: Text(text = "Postal Code: ${it.postalCode}, Country: ${it.country}")
                }


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