package edu.anayika.swapproject.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.HouseStatus
import edu.anayika.swapproject.models.Authentication

@Composable
fun ChaletView(navController: NavController, houseId: String) {
    val currentUserEmail = Authentication().getCurrentUser()?.email!!
    val address1 = remember { mutableStateOf("") }
    val address2 = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val province = remember { mutableStateOf("") }
    val postalCode = remember { mutableStateOf("") }
    val country = remember { mutableStateOf("") }
    val bedroomsQty = remember { mutableStateOf("") }
    val bedsQty = remember { mutableStateOf("") }
    val washroomsQty = remember { mutableStateOf("") }
    val balcony = remember { mutableStateOf(false) }
    val spa = remember { mutableStateOf(false) }
    val piscine = remember { mutableStateOf(false) }
    val fireplace = remember { mutableStateOf(false) }
    val internet = remember { mutableStateOf(false) }
    val television = remember { mutableStateOf(false) }
    val climatisation = remember { mutableStateOf(false) }
    val bbq = remember { mutableStateOf(false) }
    val logsChalet = remember { mutableStateOf(false) }
    val fishing = remember { mutableStateOf(false) }
    val waterfront = remember { mutableStateOf(false) }
    val waterAccess = remember { mutableStateOf(false) }
    val woodedArea = remember { mutableStateOf(false) }
    val smokersAllowed = remember { mutableStateOf(false) }
    val petsAllowed = remember { mutableStateOf(false) }
    val capacity = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
    val shortDescription = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val mainImage = remember { mutableStateOf("") }
    val images = remember { mutableStateOf("") }
    val status = remember { mutableStateOf(HouseStatus.AVAILABLE) }
    val ownerId = remember { mutableStateOf("") }
    val userId = remember { mutableStateOf("") }

    DatabaseHelper().readUserByEmail(currentUserEmail).addOnSuccessListener { results ->
        if (results != null) {
            for (result in results) {

                userId.value = result.id
            }
        }
    }

    LaunchedEffect(Unit) {
        val chaletData = DatabaseHelper().readHouse(houseId)

        title.value = chaletData.title
        shortDescription.value = chaletData.shortDescription
        description.value = chaletData.description
        mainImage.value = chaletData.mainImage
        address1.value = chaletData.address.address1
        address2.value = chaletData.address.address2
        city.value = chaletData.address.city
        province.value = chaletData.address.province
        postalCode.value = chaletData.address.postalCode
        country.value = chaletData.address.country
        bedroomsQty.value = chaletData.amenities.bedroomsQty.toString()
        bedsQty.value = chaletData.amenities.bedsQty.toString()
        washroomsQty.value = chaletData.amenities.washroomsQty.toString()
        balcony.value = chaletData.amenities.balcony
        spa.value = chaletData.amenities.spa
        piscine.value = chaletData.amenities.piscine
        fireplace.value = chaletData.amenities.fireplace
        internet.value = chaletData.amenities.internet
        television.value = chaletData.amenities.television
        climatisation.value = chaletData.amenities.climatisation
        bbq.value = chaletData.amenities.bbq
        logsChalet.value = chaletData.amenities.logsChalet
        fishing.value = chaletData.features.fishing
        waterfront.value = chaletData.features.waterfront
        waterAccess.value = chaletData.features.waterAccess
        woodedArea.value = chaletData.features.woodedArea
        smokersAllowed.value = chaletData.features.smokersAllowed
        petsAllowed.value = chaletData.features.petsAllowed
        capacity.value = chaletData.capacity.toString()
        status.value = chaletData.status
        ownerId.value = chaletData.ownerId
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = title.value,
                        style = typography.h5
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = rememberAsyncImagePainter(mainImage.value),
                            contentDescription = title.value,
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Column(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = address1.value,
                            style = typography.subtitle1
                        )
                        if(address2.value != "") {
                            Text(
                                text = address2.value,
                                style = typography.subtitle1
                            )
                        }
                        Text(
                            text = "${city.value}, ${province.value}, ${postalCode.value}",
                            style = typography.subtitle1
                        )
                        Text(
                            text = country.value,
                            style = typography.subtitle1
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Résumé",
                        style = typography.h6
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = shortDescription.value,
                        style = typography.body1
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Description",
                        style = typography.h6
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = description.value,
                        style = typography.body1
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Capacité",
                        style = typography.h6
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "${capacity.value} personnes",
                        style = typography.body1
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Caractéristiques",
                        style = typography.h6
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Nombre de chambres: ${bedroomsQty.value}",
                        style = typography.body1
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Nombre de lits: ${bedsQty.value}",
                        style = typography.body1
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Checkbox(
                                checked = balcony.value,
                                onCheckedChange = { balcony.value = it },
                                enabled = false
                            )
                            Text("Balcon")
                            Checkbox(
                                checked = spa.value,
                                onCheckedChange = { spa.value = it },
                                enabled = false
                            )
                            Text("Spa")
                            Checkbox(
                                checked = piscine.value,
                                onCheckedChange = { piscine.value = it },
                                enabled = false
                            )
                            Text("Piscine")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Checkbox(
                                checked = fireplace.value,
                                onCheckedChange = { fireplace.value = it },
                                enabled = false
                            )
                            Text("Foyer")
                            Checkbox(
                                checked = internet.value,
                                onCheckedChange = { internet.value = it },
                                enabled = false
                            )
                            Text("Internet")
                            Checkbox(
                                checked = television.value,
                                onCheckedChange = { television.value = it },
                                enabled = false
                            )
                            Text("Télévision")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Checkbox(
                                checked = climatisation.value,
                                onCheckedChange = { climatisation.value = it },
                                enabled = false
                            )
                            Text("Climatisation")
                            Checkbox(
                                checked = bbq.value,
                                onCheckedChange = { bbq.value = it },
                                enabled = false
                            )
                            Text("BBQ")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Checkbox(
                                checked = logsChalet.value,
                                onCheckedChange = { logsChalet.value = it },
                                enabled = false
                            )
                            Text("Chalet en bois rond")
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Atouts",
                        style = typography.h6
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = fishing.value,
                                onCheckedChange = { fishing.value = it },
                                enabled = false
                            )
                            Text("Pêche")

                            Checkbox(
                                checked = waterfront.value,
                                onCheckedChange = { waterfront.value = it },
                                enabled = false
                            )
                            Text("Bord de l'eau")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = waterAccess.value,
                                onCheckedChange = { waterAccess.value = it },
                                enabled = false
                            )
                            Text("Accès à l'eau")

                            Checkbox(
                                checked = woodedArea.value,
                                onCheckedChange = { woodedArea.value = it },
                                enabled = false
                            )
                            Text("Zone boisée")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = smokersAllowed.value,
                                onCheckedChange = { smokersAllowed.value = it },
                                enabled = false
                            )
                            Text("Fumeurs permis")

                            Checkbox(
                                checked = petsAllowed.value,
                                onCheckedChange = { petsAllowed.value = it },
                                enabled = false
                            )
                            Text("Animaux permis")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        if(ownerId.value != "") {
            if(ownerId.value == userId.value) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onClick = { navController.navigate("updateChalet/${houseId}") }
                    ) {
                        Text("Modifier")
                    }
                }
            }
        }
    }
}