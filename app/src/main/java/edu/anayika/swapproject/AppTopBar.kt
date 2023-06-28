package edu.anayika.swapproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppTopBar(navController: NavController, showBackButton: Boolean)  {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 10.dp, top = 0.dp, bottom = 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (showBackButton) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            navController.popBackStack()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.fillMaxHeight(),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 10.dp, top = 0.dp, bottom = 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_icon_no_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Box(
            modifier = Modifier
                .weight(4f)
                .fillMaxHeight()
                .padding(start = 5.dp, top = 0.dp, bottom = 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Chez-nous, Chez-vous",
                style = androidx.compose.material.MaterialTheme.typography.h6,
                color = MaterialTheme.colorScheme.tertiary,
                softWrap = false,
                modifier = Modifier.padding(start = 0.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
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
}
@Preview(name = "AppTopBar")
@Composable
private fun AppTopBarPreview() {
    AppTopBar(navController = rememberNavController(), showBackButton = false)
}