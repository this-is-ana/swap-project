package edu.anayika.swapproject.composables

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.data.Address
import edu.anayika.swapproject.data.Amenities
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.Features
import edu.anayika.swapproject.data.House
import edu.anayika.swapproject.data.HouseStatus
import edu.anayika.swapproject.utils.ClickOutsideToDismissKeyboard
import edu.anayika.swapproject.utils.isValidChaletUserInputs
import edu.anayika.swapproject.utils.showErrorMessage
import edu.anayika.swapproject.models.Authentication

@Composable
fun NewChaletForm(navController: NavController) {
    val currentUser = Authentication().getCurrentUser()
    var ownerId = ""
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
    val description = remember { mutableStateOf("") }
    val mainImage = remember { mutableStateOf("") }
    val images = remember { mutableStateOf("") }
    val status = remember { mutableStateOf(HouseStatus.AVAILABLE) }

    DatabaseHelper().readUserByEmail(currentUser?.email!!).addOnSuccessListener { results ->
        for(result in results) {
            ownerId = result.id
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Ajouter nouveau chalet",
                style = typography.h5,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                item {
                    Column {
                        // House details
                        Box(modifier = Modifier) {
                            ChaletDetailsView(
                                capacity = capacity,
                                description = description,
                                mainImage = mainImage,
                                images = images,
                                status = status
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        // House address
                        Box(modifier = Modifier) {
                            ChaletLocationView(
                                address1 = address1,
                                address2 = address2,
                                city = city,
                                province = province,
                                postalCode = postalCode,
                                country = country
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        // Amenities
                        Box(modifier = Modifier) {
                            ChaletAmenitiesView(
                                bedroomsQty = bedroomsQty,
                                bedsQty = bedsQty,
                                washroomsQty = washroomsQty,
                                balcony = balcony,
                                spa = spa,
                                piscine = piscine,
                                fireplace = fireplace,
                                internet = internet,
                                television = television,
                                climatisation = climatisation,
                                bbq = bbq,
                                logsChalet = logsChalet
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        // Features
                        Box(modifier = Modifier) {
                            ChaletFeaturesView(
                                fishing = fishing,
                                waterfront = waterfront,
                                waterAccess = waterAccess,
                                woodedArea = woodedArea,
                                smokersAllowed = smokersAllowed,
                                petsAllowed = petsAllowed
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        // Buttons
                        Box(modifier = Modifier.fillMaxWidth()) {
                            // Buttons
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        // Annuler button
                                        navController.popBackStack()
                                    },
                                ) {
                                    Text("Annuler")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        // Tout effacer button
                                        address1.value = ""
                                        address2.value = ""
                                        city.value = ""
                                        province.value = ""
                                        postalCode.value = ""
                                        country.value = ""
                                        bedroomsQty.value = ""
                                        bedsQty.value = ""
                                        washroomsQty.value = ""
                                        balcony.value = false
                                        spa.value = false
                                        piscine.value = false
                                        fireplace.value = false
                                        internet.value = false
                                        television.value = false
                                        climatisation.value = false
                                        bbq.value = false
                                        logsChalet.value = false
                                        fishing.value = false
                                        waterfront.value = false
                                        waterAccess.value = false
                                        woodedArea.value = false
                                        smokersAllowed.value = false
                                        petsAllowed.value = false
                                        capacity.value = ""
                                        description.value = ""
                                        mainImage.value = ""
                                        images.value = ""
                                        status.value = HouseStatus.AVAILABLE
                                    },
                                ) {
                                    Text("Tout effacer")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        // Soumettre button
                                        val address = Address(
                                            address1 = address1.value,
                                            address2 = address2.value,
                                            city = city.value,
                                            province = province.value,
                                            postalCode = postalCode.value,
                                            country = country.value
                                        )
                                        val amenities = Amenities(
                                            bedroomsQty = bedroomsQty.value.toIntOrNull() ?: 0,
                                            bedsQty = bedsQty.value.toIntOrNull() ?: 0,
                                            washroomsQty = washroomsQty.value.toIntOrNull() ?: 0,
                                            balcony = balcony.value,
                                            spa = spa.value,
                                            piscine = piscine.value,
                                            fireplace = fireplace.value,
                                            internet = internet.value,
                                            television = television.value,
                                            climatisation = climatisation.value,
                                            bbq = bbq.value,
                                            logsChalet = logsChalet.value
                                        )
                                        val features = Features(
                                            fishing = fishing.value,
                                            waterfront = waterfront.value,
                                            waterAccess = waterAccess.value,
                                            woodedArea = woodedArea.value,
                                            smokersAllowed = smokersAllowed.value,
                                            petsAllowed = petsAllowed.value
                                        )
                                        val house = House(
                                            capacity = capacity.value.toIntOrNull() ?: 0,
                                            features = features,
                                            amenities = amenities,
                                            description = description.value,
                                            mainImage = mainImage.value,
                                            images = images.value.split(",").toTypedArray(),
                                            status = status.value,
                                            address = address,
                                            ownerId = ownerId
                                        )

                                        // Call the function to save the validated inputs to Firestore
                                        createNewChalet(house, navController, context)
                                    },
                                ) {
                                    Text("Soumettre")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun createNewChalet(chalet: House, navController: NavController, context: Context) {
    val errMsg: String

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

    if (isValidChaletUserInputs(chalet)) {
        DatabaseHelper().createHouse(hashHouse)

        navController.navigate("userProfileAccount")
    } else {
        errMsg = "Informations du chalet invalides"
        showErrorMessage(errMsg, context)
    }
}

@Composable
fun ChaletAmenitiesView(
    bedroomsQty: MutableState<String>,
    bedsQty: MutableState<String>,
    washroomsQty: MutableState<String>,
    balcony: MutableState<Boolean>,
    spa: MutableState<Boolean>,
    piscine: MutableState<Boolean>,
    fireplace: MutableState<Boolean>,
    internet: MutableState<Boolean>,
    television: MutableState<Boolean>,
    climatisation: MutableState<Boolean>,
    bbq: MutableState<Boolean>,
    logsChalet: MutableState<Boolean>
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Installations",
                textAlign = TextAlign.Center,
                style =  typography.h6
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = bedroomsQty.value,
                    onValueChange = { bedroomsQty.value = it },
                    label = { Text("Nombre de chambres", textAlign = TextAlign.End) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = bedsQty.value,
                    onValueChange = { bedsQty.value = it },
                    label = { Text("Nombre de lits") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = washroomsQty.value,
                    onValueChange = { washroomsQty.value = it },
                    label = { Text("Nombre de salles de bain") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = balcony.value,
                    onCheckedChange = { balcony.value = it },
                )
                Text("Balcon")
                Checkbox(
                    checked = spa.value,
                    onCheckedChange = { spa.value = it },
                )
                Text("Spa")
                Checkbox(
                    checked = piscine.value,
                    onCheckedChange = { piscine.value = it },
                )
                Text("Piscine")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = fireplace.value,
                    onCheckedChange = { fireplace.value = it },
                )
                Text("Foyer")
                Checkbox(
                    checked = internet.value,
                    onCheckedChange = { internet.value = it },
                )
                Text("Internet")
                Checkbox(
                    checked = television.value,
                    onCheckedChange = { television.value = it },
                )
                Text("Télévision")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = climatisation.value,
                    onCheckedChange = { climatisation.value = it },
                )
                Text("Climatisation")
                Checkbox(
                    checked = bbq.value,
                    onCheckedChange = { bbq.value = it },
                )
                Text("BBQ")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = logsChalet.value,
                    onCheckedChange = { logsChalet.value = it },
                )
                Text("Chalet en bois rond")
            }
        }
    }
}

@Composable
fun ChaletDetailsView(
    capacity: MutableState<String>,
    description: MutableState<String>,
    mainImage: MutableState<String>,
    images: MutableState<String>,
    status: MutableState<HouseStatus>
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Caractéristiques",
                textAlign = TextAlign.Center,
                style = typography.h6
            )
            OutlinedTextField(
                value = capacity.value,
                onValueChange = { capacity.value = it },
                label = { Text("Capacité") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = mainImage.value,
                onValueChange = { mainImage.value = it },
                label = { Text("Image principale") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = images.value,
                onValueChange = { images.value = it },
                label = { Text("Images") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            val statusOptions = listOf(HouseStatus.AVAILABLE, HouseStatus.NOT_AVAILABLE)
            val selectedStatus = remember { mutableStateOf(HouseStatus.AVAILABLE) }
            val isDropdownExpanded = remember { mutableStateOf(false) }
            ChaletStatusDropdownMenu(
                statusOptions,
                selectedStatus,
                isDropdownExpanded
            )
        }
    }
}

@Composable
fun ChaletFeaturesView(
    fishing: MutableState<Boolean>,
    waterfront: MutableState<Boolean>,
    waterAccess: MutableState<Boolean>,
    woodedArea: MutableState<Boolean>,
    smokersAllowed: MutableState<Boolean>,
    petsAllowed: MutableState<Boolean>
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Atouts",
                textAlign = TextAlign.Center,
                style =  typography.h6
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = fishing.value,
                    onCheckedChange = { fishing.value = it },
                )
                Text("Pêche")

                Checkbox(
                    checked = waterfront.value,
                    onCheckedChange = { waterfront.value = it },
                )
                Text("Bord de l'eau")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = waterAccess.value,
                    onCheckedChange = { waterAccess.value = it },
                )
                Text("Accès à l'eau")

                Checkbox(
                    checked = woodedArea.value,
                    onCheckedChange = { woodedArea.value = it },
                )
                Text("Zone boisée")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = smokersAllowed.value,
                    onCheckedChange = { smokersAllowed.value = it },
                )
                Text("Fumeurs permis")

                Checkbox(
                    checked = petsAllowed.value,
                    onCheckedChange = { petsAllowed.value = it },
                )
                Text("Animaux permis")
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ChaletLocationView(
    address1: MutableState<String>,
    address2: MutableState<String>,
    city: MutableState<String>,
    province: MutableState<String>,
    postalCode: MutableState<String>,
    country: MutableState<String>
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Adresse",
                textAlign = TextAlign.Center,
                style = typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
                // Address
            ClickOutsideToDismissKeyboard {
                OutlinedTextField(
                    value = address1.value,
                    onValueChange = { address1.value = it },
                    label = { Text("Adresse 1") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
                OutlinedTextField(
                    value = address2.value,
                    onValueChange = { address2.value = it },
                    label = { Text("Adresse 2") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
                OutlinedTextField(
                    value = city.value,
                    onValueChange = { city.value = it },
                    label = { Text("Ville") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
                OutlinedTextField(
                    value = province.value,
                    onValueChange = { province.value = it },
                    label = { Text("Province") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
                OutlinedTextField(
                    value = postalCode.value,
                    onValueChange = { postalCode.value = it },
                    label = { Text("Code postal") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ClickOutsideToDismissKeyboard {
                OutlinedTextField(
                    value = country.value,
                    onValueChange = { country.value = it },
                    label = { Text("Pays") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ChaletStatusDropdownMenu(
    statusOptions: List<HouseStatus>,
    selectedStatus: MutableState<HouseStatus>,
    isDropdownExpanded: MutableState<Boolean>
) {
    var expanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        Text(text = "Status")
        statusOptions.forEach { statusOption ->
            DropdownMenuItem(
                text = { Text(statusOption.name) },
                onClick = {
                    selectedStatus.value = statusOption
                    isDropdownExpanded.value = false
                }
            )
        }
    }
    Text(text = selectedStatus.value.name)
}

@Preview(name = "NewChaletForm")
@Composable
private fun PreviewNewChaletForm() {
    NewChaletForm(navController = rememberNavController())
}