package edu.anayika.swapproject.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.anayika.swapproject.R
import edu.anayika.swapproject.data.CurrentActivity

@Composable
fun AppTopBar(
    navController: NavController,
    showBackButton: Boolean,
    currentActivity: CurrentActivity
) {
    val dropdownItems = getDropDownItems(currentActivity)

    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (showBackButton) {
                BackButton(onClick = { navController.popBackStack() })
            }
            AppLogoWithTitle()
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                DropDownMenuButton(navController,dropdownItems)
            }
        }
    }
}

@Composable
fun getDropDownItems(currentActivity: CurrentActivity): List<String> {
    return when (currentActivity) {
        CurrentActivity.MainActivity, CurrentActivity.CreateAccountActivity -> {
            listOf("Contactez-nous")
        }
        CurrentActivity.ContactUsActivity -> {
            listOf("Quitter la Session")
        }
        else -> {
            listOf(
                //"Mon Profil",
                //"Rechercher Chalets",
                //"Mes chalets favoris",
                //"Mes correspondances",
                "Contactez-nous",
                "Quitter la Session"
            )
        }
    }
}

@Composable
fun DropDownMenuButton(
    navController: NavController,
    dropdownItems: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "menu",
                modifier = Modifier.fillMaxHeight(),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            dropdownItems.forEach { item ->
                DropdownItem(item, navController)
            }
        }
    }
}

@Composable
fun DropdownItem(item: String, navController: NavController) {
    val context = LocalContext.current
    DropdownMenuItem(onClick = {
        when (item) {
           // "Mon Profil" -> navController.navigate("userProfileAccount")
           // "Rechercher Chalets" -> navController.navigate("searchChalets")
           // "Mes chalets favoris" -> navController.navigate("favoriteChalets")
           // "Mes correspondances" -> navController.navigate("myCorrespondences")
            "Quitter la Session" -> signOut(navController)
            "Contactez-nous" -> navController.navigate("contactUs")
            else -> {
                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
            }
        }
    }) {
        Text(
            text = item,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

fun signOut(navController: NavController) {
    Firebase.auth.signOut()

    navController.navigate("login")
}

@Composable
fun AppLogoWithTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppLogo()
        Text(
            text = "Chez-nous, Chez-vous",
            style = androidx.compose.material.MaterialTheme.typography.h6,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AppLogo() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 5.dp, top = 0.dp, bottom = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon_no_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight()
        )
    }
}




@Preview(name = "AppTopBar")
@Composable
private fun AppTopBarPreview() {
    val navController = rememberNavController()
    val showBackButton = true
    val currentActivity = CurrentActivity.MainActivity

    AppTopBar(navController = navController, showBackButton = showBackButton, currentActivity = currentActivity)
}
