package edu.anayika.swapproject.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import edu.anayika.swapproject.R
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.User
import edu.anayika.swapproject.data.UserType
import edu.anayika.swapproject.models.Authentication


@Composable
fun UserSessionView(navController: NavController) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

            ) {
                TopSessionBar(navController = navController)
            }
            Spacer(modifier = Modifier.height(1.dp))
                    UserSessionMainSection(navController)
                }
            }
        }



@Composable
fun TopSessionBar(navController: NavController) {
    Surface(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxSize()
                .padding(start = 8.dp)
        ) {
            TopSessionContainer(navController)
        }
    }
}

@Composable
fun TopSessionContainer(navController: NavController) {
    val userProfileIcon = R.drawable.app_icon_no_bg
    val houseUserListIcon = R.drawable.house_user_list_icon
    val addHouseIcon = R.drawable.add_house_icon
    val houseSearchIcon = R.drawable.house_search_icon
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularContainer(imageRessource = userProfileIcon) {
            navController.navigate("userProfileAccount")
        }
        Spacer(modifier = Modifier.width(8.dp))
        CircularContainer(imageRessource = houseUserListIcon) {
            navController.navigate("userChaletList")
        }
        Spacer(modifier = Modifier.width(8.dp))
        CircularContainer(imageRessource = addHouseIcon ) {
            navController.navigate("addNewChalet")
        }
        Spacer(modifier = Modifier.width(8.dp))
        CircularContainer(imageRessource = houseSearchIcon
        ) {
            navController.navigate("searchChalets")
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CircularContainer(imageRessource: Int, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .aspectRatio(1F)
            .fillMaxHeight()
            .padding(2.dp)
            .shadow(2.dp, CircleShape)
    ) {
            Image(
                painter = rememberAsyncImagePainter(imageRessource),
                contentDescription = "icon${imageRessource}",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
    }
}

@Composable
fun UserSessionMainSection(navController: NavController) {
    val email = Authentication().getCurrentUser()?.email!!
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    lateinit var user: User

    Thread.sleep(1000)

    DatabaseHelper().readUserByEmail(email).addOnSuccessListener { results ->
        for (result in results) {
            user = User(
                result.data["email"].toString(),
                result.data["firstName"].toString(),
                result.data["lastName"].toString(),
                result.data["phone"].toString(),
                UserType.REGULAR
            )

            firstName.value = user.firstName
            lastName.value = user.lastName
        }
    }
    Surface(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier

                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp),
                text = "Bienvenue ${firstName.value} ${lastName.value} !",
                style = androidx.compose.material.MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun LeftSideColumnBar(navController: NavController) {
    Surface(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .fillMaxHeight()

        ) {
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
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}


@Preview(name = "UserSessionView")
@Composable
private fun PreviewUserSessionView() {
    UserSessionView(navController = rememberNavController())
}