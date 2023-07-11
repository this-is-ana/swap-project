package edu.anayika.swapproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.anayika.swapproject.ui.theme.SwapProjectTheme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.data.Address
import edu.anayika.swapproject.data.Amenities
import edu.anayika.swapproject.data.House
import edu.anayika.swapproject.data.HouseStatus
import edu.anayika.swapproject.data.CurrentActivity
import edu.anayika.swapproject.data.Features

class NewChaletActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NewChaletForm(navController)
        }
    }
}

@Composable
fun NewChaletForm(navController: NavHostController) {
    val chalet = House(
        capacity = 0,
        features = Features(true, true, true, true, true, true),
        amenities = Amenities(1,1,1, true, true, true, true, true, true, true, true, true),
        description = "",
        mainImage = "",
        images = emptyArray(),
        status = HouseStatus.AVAILABLE,
        address = Address("", "", "", "", "", ""),
        ownerId = ""
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Add a new Chalet to your account:")
        OutlinedTextField(
            value = TextFieldValue(chalet.capacity.toString()),
            onValueChange = { chalet.capacity = it.text.toInt() },
            label = { Text("Size") },
            modifier = Modifier.fillMaxWidth()
        )
        // Add more TextField components for other chalet properties
        OutlinedTextField(
            value = TextFieldValue(chalet.description),
            onValueChange = { chalet.description = it.text },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

            Button(
                onClick = { /* Handle submit button click */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Submit")
            }

    }
}

@Preview(showBackground = true)
@Composable
fun NewChaletFormPreview() {
    SwapProjectTheme {
        NewChaletForm(navController = rememberNavController())
    }
}