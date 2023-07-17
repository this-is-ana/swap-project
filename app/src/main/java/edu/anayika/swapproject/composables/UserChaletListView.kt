package edu.anayika.swapproject.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
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
import edu.anayika.swapproject.models.Authentication
import edu.anayika.swapproject.models.UserProfileViewModel

@Composable
fun UserChaletListView(navController: NavController) {

    val currentUserEmail = Authentication().getCurrentUser()?.email!!
    val chalets = remember { mutableStateListOf<House>() }

    LaunchedEffect(Unit) {
        val ownerId = DatabaseHelper().readUserIdByEmail(currentUserEmail)
        val chaletsData = DatabaseHelper().readHouses(ownerId)

        for(chalet in chaletsData) {
            chalets.add(chalet)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(chalets.size) { index ->
                if(chalets.size > 0) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                navController.navigate("chaletSoloView/${chalets[index].documentId}")
                            }
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
                                contentDescription = chalets[index].title,
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

        //NewChaletAddButton(navController)
    }
}


/*
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
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { navController.navigate("addNewChalet") }
        ) {
            Text(
                text = "Ajouter un chalet",
                style = typography.h6
            )
        }
    }
}
@Preview(name = "MyChaletsListView")
@Composable
private fun PreviewMyChaletsListView() {
    val viewModel = UserProfileViewModel()
    UserChaletListView(navController = rememberNavController())
}
