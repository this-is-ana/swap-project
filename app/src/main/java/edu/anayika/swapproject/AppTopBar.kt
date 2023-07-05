package edu.anayika.swapproject

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppTopBar(navController: NavController, showBackButton: Boolean) {
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
                MenuButton()
            }
        }
    }
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

@Composable
fun MenuButton() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = null,
            modifier = Modifier.fillMaxHeight(),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(name = "AppTopBar")
@Composable
private fun AppTopBarPreview() {
    AppTopBar(navController = rememberNavController(), showBackButton = true)
}
