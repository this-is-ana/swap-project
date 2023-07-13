package edu.anayika.swapproject.composables

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

@Composable
fun NewChaletForm(navController: NavController) {
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

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Ajouter nouveau Chalet au profil:",
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
                    }
                }
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
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Annuler")
                    }
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
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(text = "Tout effacer")
                    }
                    Button(
                        onClick = {
                            // Sousmettre button
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
                                ownerId = ""
                            )
                            // Call the function to save the validated inputs to Firestore
                            //CreateNewChalet(house, navController)
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = "Sousmettre")
                    }
                }
            }
            }
        }
    }

@Composable
fun CreateNewChalet(newChaletUserInputs: House, navController: NavController) {

        val context = LocalContext.current
        val navController = rememberNavController()
        var errMsg = ""
        if (isValidChaletUserInputs(newChaletUserInputs)) {
            val house = newChaletUserInputs
            DatabaseHelper.createHouse(house)
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
                text = "Amenities",
                textAlign = TextAlign.Center,
                style =  typography.h6
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = bedroomsQty.value,
                    onValueChange = { bedroomsQty.value = it },
                    label = { Text("Bedrooms Qty", textAlign = TextAlign.End) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = bedsQty.value,
                    onValueChange = { bedsQty.value = it },
                    label = { Text("Beds Qty") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = washroomsQty.value,
                    onValueChange = { washroomsQty.value = it },
                    label = { Text("Washrooms Qty") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = balcony.value,
                    onCheckedChange = { balcony.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Balcony")

                Checkbox(
                    checked = spa.value,
                    onCheckedChange = { spa.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Spa")

                Checkbox(
                    checked = piscine.value,
                    onCheckedChange = { piscine.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Piscine")

            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = fireplace.value,
                    onCheckedChange = { fireplace.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Fireplace")

                Checkbox(
                    checked = internet.value,
                    onCheckedChange = { internet.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Internet")

                Checkbox(
                    checked = television.value,
                    onCheckedChange = { television.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Television")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = climatisation.value,
                    onCheckedChange = { climatisation.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Climatisation")

                Checkbox(
                    checked = bbq.value,
                    onCheckedChange = { bbq.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("BBQ")

                Checkbox(
                    checked = logsChalet.value,
                    onCheckedChange = { logsChalet.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Logs Chalet")
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
                label = { Text("Capacity") },
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
                label = { Text("Main Image") },
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
                text = "Features",
                textAlign = TextAlign.Center,
                style =  typography.h6
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = fishing.value,
                    onCheckedChange = { fishing.value = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = "Fishing",
                    textAlign = TextAlign.Start
                )

                Checkbox(
                    checked = waterfront.value,
                    onCheckedChange = { waterfront.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Waterfront",
                    textAlign = TextAlign.Center
                )

                Checkbox(
                    checked = waterAccess.value,
                    onCheckedChange = { waterAccess.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Water Access",
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = woodedArea.value,
                    onCheckedChange = { woodedArea.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Wooded Area")

                Checkbox(
                    checked = smokersAllowed.value,
                    onCheckedChange = { smokersAllowed.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Smokers Allowed")

                Checkbox(
                    checked = petsAllowed.value,
                    onCheckedChange = { petsAllowed.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Pets Allowed")
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
                text = "Localisation",
                textAlign = TextAlign.Center,
                style = typography.h6
            )
            ClickOutsideToDismissKeyboard {
                // Address
                OutlinedTextField(
                    value = address1.value,
                    onValueChange = { address1.value = it },
                    label = { Text("Address 1") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = address2.value,
                    onValueChange = { address2.value = it },
                    label = { Text("Address 2") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = city.value,
                    onValueChange = { city.value = it },
                    label = { Text("City") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = province.value,
                    onValueChange = { province.value = it },
                    label = { Text("Province") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = postalCode.value,
                    onValueChange = { postalCode.value = it },
                    label = { Text("Postal Code") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = country.value,
                    onValueChange = { country.value = it },
                    label = { Text("Country") },
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
