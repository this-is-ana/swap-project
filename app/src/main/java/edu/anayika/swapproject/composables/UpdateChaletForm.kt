package edu.anayika.swapproject.composables

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.anayika.swapproject.data.Address
import edu.anayika.swapproject.data.Amenities
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.Features
import edu.anayika.swapproject.data.House
import edu.anayika.swapproject.data.HouseStatus
import edu.anayika.swapproject.utils.ClickOutsideToDismissKeyboard
import edu.anayika.swapproject.utils.isValidChaletUserInputs
import edu.anayika.swapproject.utils.showErrorMessage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UpdateChaletView(navController: NavController, houseId: String) {
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

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Modification de chalet",
                style = MaterialTheme.typography.h5,
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(18.dp))
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                item {
                    Column {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Adresse", textAlign = TextAlign.Center, style = MaterialTheme.typography.h6
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                // Address
                                ClickOutsideToDismissKeyboard {
                                    TextFieldWithLabel(
                                        value = address1.value,
                                        onValueChange = { address1.value = it },
                                        label = "Adresse 1"
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ClickOutsideToDismissKeyboard {
                                    TextFieldWithLabel(
                                        value = address2.value,
                                        onValueChange = { address2.value = it },
                                        label = "Adresse 2"
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ClickOutsideToDismissKeyboard {
                                    TextFieldWithLabel(
                                        value = city.value, onValueChange = { city.value = it }, label = "Ville"
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ClickOutsideToDismissKeyboard {
                                    TextFieldWithLabel(
                                        value = province.value,
                                        onValueChange = { province.value = it },
                                        label = "Province"
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ClickOutsideToDismissKeyboard {
                                    TextFieldWithLabel(
                                        value = postalCode.value,
                                        onValueChange = { postalCode.value = it },
                                        label = "Code postal"
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ClickOutsideToDismissKeyboard {
                                    TextFieldWithLabel(
                                        value = country.value, onValueChange = { country.value = it }, label = "Pays"
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Caractéristiques", textAlign = TextAlign.Center, style = MaterialTheme.typography.h6
                                )
                                TextFieldWithLabel(
                                    label = "Titre",
                                    value = title.value,
                                    onValueChange = { title.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Description courte",
                                    value = shortDescription.value,
                                    onValueChange = { shortDescription.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Description",
                                    value = description.value,
                                    onValueChange = { description.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                NumberTextFieldWithLabel(
                                    value = capacity.value,
                                    onValueChange = { capacity.value = it },
                                    label = "Nombre maximal de personnes"
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Lien image principale",
                                    value = mainImage.value,
                                    onValueChange = { mainImage.value = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextFieldWithLabel(
                                    label = "Images additionnelles",
                                    value = images.value,
                                    onValueChange = { images.value = it }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                val statusOptions = listOf(HouseStatus.AVAILABLE, HouseStatus.NOT_AVAILABLE)
                                val selectedStatus = remember { mutableStateOf(HouseStatus.AVAILABLE) }
                                ChaletStatusDropdownMenu(
                                    statusOptions = statusOptions,
                                    selectedStatus = selectedStatus
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Installations", textAlign = TextAlign.Center, style = MaterialTheme.typography.h6
                            )
                            Column(modifier = Modifier.fillMaxWidth()) {
                                NumberTextFieldWithLabel(
                                    value = bedroomsQty.value,
                                    onValueChange = { bedroomsQty.value = it },
                                    label = "Nombre de chambres",

                                    )
                                Spacer(modifier = Modifier.height(8.dp))
                                NumberTextFieldWithLabel(
                                    value = bedsQty.value,
                                    onValueChange = { bedsQty.value = it },
                                    label = "Nombre de lits"

                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                NumberTextFieldWithLabel(
                                    value = washroomsQty.value,
                                    onValueChange = { washroomsQty.value = it },
                                    label = "Nombre de salles de bain"
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
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

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Checkbox(
                                        checked = logsChalet.value,
                                        onCheckedChange = { logsChalet.value = it },
                                    )
                                    Text("Chalet en bois rond")
                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Text(
                                    text = "Atouts", textAlign = TextAlign.Center, style = MaterialTheme.typography.h6
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
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

                        Spacer(modifier = Modifier.height(12.dp))

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
                                    title = title.value,
                                    shortDescription = shortDescription.value,
                                    description = description.value,
                                    mainImage = mainImage.value,
                                    //images = images.value.split(",").toTypedArray(),
                                    images = "1",
                                    status = status.value,
                                    address = address,
                                    ownerId = ownerId.value
                                )

                                updateChalet(house, navController, context, houseId)
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

fun updateChalet(chalet: House, navController: NavController, context: Context, houseId: String) {
    val errMsg: String

    val features = chalet.features
    val amenities = chalet.amenities
    val address = chalet.address

    val hashHouse = hashMapOf<String, Any>(
        "capacity" to chalet.capacity,
        "title" to chalet.title,
        "shortDescription" to chalet.shortDescription,
        "description" to chalet.description,
        "mainImage" to chalet.mainImage,
        "images" to chalet.images,
        "status" to chalet.status,
        "ownerId" to chalet.ownerId,
    )

    if (isValidChaletUserInputs(chalet)) {
        DatabaseHelper().updateHouse(hashHouse, features, amenities, address, houseId)

        navController.navigate("userProfileAccount")
    } else {
        errMsg = "Informations de chalet invalides"
        showErrorMessage(errMsg, context)
    }
}