package edu.anayika.swapproject.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.House
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
    UserProfileSection(user)
    Spacer(modifier = Modifier.height(16.dp))
    NewChaletAddButton(navController)
    Spacer(modifier = Modifier.height(16.dp))
    //UserChaletsListView(navController, user)
}

@Composable
fun UserProfileSection(user: User) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        Column(modifier = Modifier.fillMaxHeight()) {

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
/*
    @Composable
    fun UserChaletsListView(navController: NavController, user: User) {
        OutlinedCard(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                var usersChalets: Array<House>? = null
                items() { usersChalets ->
                   // ChaletCard(chalet = chalet)
                }
            }
        }
    }
    @Composable
    fun ChaletCard(chalet: House) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            elevation = 4.dp
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Left Image
                Image(
                    painter = painterResource(chalet.imageResId), // Replace with actual image resource
                    contentDescription = null,
                    modifier = Modifier.size(80.dp).clip(shape = RoundedCornerShape(8.dp))
                )

                // Right Column
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = chalet.name, style = typography.h6)
                    Text(text = "Chalet ${chalet.number}")
                    Text(text = "${chalet.city}, ${chalet.province}, ${chalet.country}")
                }
            }
        }
    }
*/
    @Composable
    fun NewChaletAddButton(navController: NavController) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Button(modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { navController.navigate("addNewChalet") }
            ) {
                Text(
                    text = "Ajouter un chalet",
                    style = typography.h6
                )
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