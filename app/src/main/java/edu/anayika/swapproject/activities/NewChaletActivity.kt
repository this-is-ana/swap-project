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
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.data.Address
import edu.anayika.swapproject.data.Amenities
import edu.anayika.swapproject.data.House
import edu.anayika.swapproject.data.HouseStatus
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.Features
import edu.anayika.swapproject.models.Authentication

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
    val capacity = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val currentUser = Authentication().getCurrentUser()
    var ownerId = ""
    lateinit var chalet: House

    DatabaseHelper().readUserByEmail(currentUser?.email!!).addOnSuccessListener { results ->
        for(result in results) {
           ownerId = result.id
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Ajouter un nouveau chalet")
        OutlinedTextField(
            value = capacity.value,
            onValueChange = { capacity.value = it },
            label = { Text("Capacité") },
            modifier = Modifier.fillMaxWidth()
        )
        // Add more TextField components for other chalet properties
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
            Button(
                onClick = {
                    chalet = House(
                        capacity = capacity.value.toInt(),
                        features = Features(
                            fishing = false,
                            waterfront = true,
                            waterAccess = true,
                            woodedArea = true,
                            smokersAllowed = true,
                            petsAllowed = true
                        ),
                        amenities = Amenities(1,1,1,
                            balcony = true,
                            spa = true,
                            piscine = true,
                            fireplace = true,
                            internet = true,
                            television = true,
                            climatisation = true,
                            bbq = true,
                            logsChalet = true
                        ),
                        description = description.value,
                        mainImage = "",
                        images = emptyArray(),
                        status = HouseStatus.AVAILABLE,
                        address = Address("123 rue Ciel", "App 2", "Montréal", "Québec", "H2H 2H2", "Canada"),
                        ownerId = ownerId
                    )

                    val hashFeatures = hashMapOf<String, Any> (
                        "fishing" to chalet.features.fishing,
                        "waterfront" to chalet.features.waterfront,
                        "waterAccess" to chalet.features.waterAccess,
                        "woodedArea" to chalet.features.woodedArea,
                        "smokersAllowed" to chalet.features.smokersAllowed,
                        "petsAllowed" to chalet.features.petsAllowed
                    )
                    val hashAmenities = hashMapOf<String, Any>(
                        "bedroomsQty" to chalet.amenities.bedroomsQty,
                        "bedsQty" to chalet.amenities.bedsQty,
                        "washroomsQty" to chalet.amenities.washroomsQty,
                        "balcony" to chalet.amenities.balcony,
                        "spa" to chalet.amenities.spa,
                        "piscine" to chalet.amenities.piscine,
                        "fireplace" to chalet.amenities.fireplace,
                        "internet" to chalet.amenities.internet,
                        "television" to chalet.amenities.television,
                        "climatisation" to chalet.amenities.climatisation,
                        "bbq" to chalet.amenities.bbq,
                        "logsChalet" to chalet.amenities.logsChalet
                    )
                    val hashAddress = hashMapOf<String, Any>(
                        "address1" to chalet.address.address1,
                        "address2" to chalet.address.address2,
                        "city" to chalet.address.city,
                        "province" to chalet.address.province,
                        "postalCode" to chalet.address.postalCode,
                        "country" to chalet.address.country
                    )
                    val hashHouse = hashMapOf(
                        "capacity" to chalet.capacity,
                        "features" to hashFeatures,
                        "amenities" to hashAmenities,
                        "description" to chalet.description,
                        "mainImage" to chalet.mainImage,
                        "images" to {},
                        "status" to chalet.status,
                        "address" to hashAddress,
                        "ownerId" to chalet.ownerId,
                    )

                    DatabaseHelper().createHouse(hashHouse)

                    navController.navigate("userProfileAccount")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Enregistrer",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.h6
                )
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