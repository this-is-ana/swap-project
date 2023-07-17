package edu.anayika.swapproject.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.House
import edu.anayika.swapproject.data.User
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
    val hidden = remember{ mutableStateOf(true) }
    val chalets = remember { mutableStateListOf<House>() }
    lateinit var user: User
    var userId = ""

    DatabaseHelper().readUserByEmail(currentUserEmail).addOnSuccessListener { results ->
        if (results != null) {
            for (result in results) {

                userId = result.id

                user = User(
                    result.data["email"].toString(),
                    result.data["firstName"].toString(),
                    result.data["lastName"].toString(),
                    result.data["phone"].toString(),
                    enumValueOf(result["userType"].toString())
                )

                email.value = user.email
                firstName.value = user.firstName
                lastName.value = user.lastName
                phone.value = user.phone

                if (user.userType.toString() == "PREMIUM") {
                    userType.value = "Premium"
                } else {
                    userType.value = "Régulier"
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        val ownerId = DatabaseHelper().readUserIdByEmail(currentUserEmail)
        val chaletsData = DatabaseHelper().readHousesByOwner(ownerId)

        for(chalet in chaletsData) {
            chalets.add(chalet)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        LazyColumn {
            item {
                Column {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Nom: ${firstName.value} ${lastName.value}",
                                style = typography.subtitle1
                            )
                            Text(
                                text = "Courriel: ${email.value}",
                                style = typography.subtitle1
                            )
                            Text(text = "Téléphone: ${phone.value}", style = typography.subtitle1)
                            Text(text = "Abonnement: ${userType.value}", style = typography.subtitle1)

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                onClick = { hidden.value = !(hidden.value) }
                            ) {
                                Text("Modifier")
                            }

                            if(!hidden.value) {
                                Spacer(modifier = Modifier.height(12.dp))

                                TextFieldWithLabel(
                                    label = "Prénom",
                                    value = firstName.value,
                                    onValueChange = { firstName.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Nom de famille",
                                    value = lastName.value,
                                    onValueChange = { lastName.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Courriel",
                                    value = email.value,
                                    onValueChange = { email.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Téléphone",
                                    value = phone.value,
                                    onValueChange = { phone.value = it }
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(
                                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                    onClick = {
                                        user.firstName = firstName.value
                                        user.lastName = lastName.value
                                        user.email = email.value
                                        user.phone = phone.value

                                        DatabaseHelper().updateUser(user, userId)

                                        hidden.value = !(hidden.value)
                                    }
                                ) {
                                    Text("Enregistrer")
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Mes chalets",
                        style = typography.h5,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            items(chalets.size) { index ->
                if(chalets.size > 0) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = chalets[index].title,
                                style = typography.subtitle1
                            )

                            Image(
                                painter = rememberAsyncImagePainter(chalets[index].mainImage),

                                contentDescription = null,
                                modifier = Modifier.size(128.dp)
                            )

                            Text(
                                text = chalets[index].shortDescription,
                                style = typography.subtitle1
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
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