package edu.anayika.swapproject.composables

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.auth.User
import edu.anayika.swapproject.activities.NewChaletActivity
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.data.House
import edu.anayika.swapproject.models.UserProfileViewModel

@Composable
fun UserChaletListView(navController: NavController) {
    OutlinedCard(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            var usersChalets: Array<House>? = null
          //  items() { usersChalets ->
                // ChaletCard(chalet = chalet)
            }
        NewChaletAddButton(navController)
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
        Button(modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { navController.navigate("addNewChalet") }
        ) {
            androidx.compose.material.Text(
                text = "Ajouter un chalet",
                style = androidx.compose.material.MaterialTheme.typography.h6
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
