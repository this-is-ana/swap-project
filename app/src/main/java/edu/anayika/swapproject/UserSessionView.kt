package edu.anayika.swapproject


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import coil.compose.rememberImagePainter


@Composable
fun UserSessionView(navController: NavController) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()
                .height(50.dp)) {
                TopSessionBar(navController = navController)
            }
            Spacer(modifier = Modifier.height(1.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    LeftSideColumnBar(navController = navController)
                }
                Spacer(modifier = Modifier.width(1.dp))
                Column(modifier = Modifier.weight(4f)) {
                    UserSessionMainColumn(navController)
                }
            }
        }
    }
}

@Composable
fun TopSessionBar(navController: NavController) {
    Surface(modifier = Modifier
    ){
        Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .fillMaxSize()){
            TopSessionContainers(navController) }
    }
}

@Composable
fun TopSessionContainers(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularContainer(imageResource = R.drawable.app_icon_no_bg) {
            // Handle click action here
        }
        CircularContainer(imageResource = R.drawable.app_icon_no_bg) {
            // Handle click action here
        }
        CircularContainer(imageResource = R.drawable.app_icon_no_bg) {
            // Handle click action here
        }
        CircularContainer(imageResource = R.drawable.app_icon_no_bg) {
            // Handle click action here
        }
        CircularContainer(imageResource = R.drawable.app_icon_no_bg) {
            // Handle click action here
        }
    }
}

@Composable
fun CircularContainer(imageResource: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        // Replace the content with your image or icon
        Image(
            painter = rememberImagePainter(data = imageResource),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun UserSessionMainColumn(navController: NavController) {
    Surface(modifier = Modifier
            ){
        Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxSize()){
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp),
                text = "Bienvenue \"User\"!",
                style = androidx.compose.material.MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colorScheme.primary) }
    }
}


@Composable
fun LeftSideColumnBar(navController: NavController){
    Surface(modifier = Modifier
    ){
        Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .fillMaxHeight()

        ){
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 15.sp
                    )
                ) {
                    append("Left Side Column")
                }
            },
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(8.dp),
        style = androidx.compose.material.MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colorScheme.primary)
        }
    }

}


@Preview(name = "UserSessionView")
@Composable
private fun PreviewUserSessionView() {
    UserSessionView(navController = rememberNavController())
}