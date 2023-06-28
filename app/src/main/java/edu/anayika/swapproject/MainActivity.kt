package edu.anayika.swapproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.theme.SwapProject_Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppComposable() {
    // M3 composables
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapProject_Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),)
    {
        Column(
            modifier = Modifier.fillMaxSize())
        {
            AppTopBar(navController = navController, showBackButton = false)
            NavHost(navController, startDestination = "login") {
                composable("login") { LoginPage(navController = navController) }
                composable("createAccount") { CreateAccountActivity() }
            }
        }
    }
}

@Composable
fun AppTopBar(navController: NavController, showBackButton: Boolean = false)  {
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
                .padding(start = 10.dp, top = 0.dp, bottom = 0.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Chez-nous, Chez-vous",
                style = typography.h6,
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



@Composable
fun LoginPage(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Sign in",
            style = typography.h5,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.Start),
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
                onClick = { /* Handle login logic here */ }
            ) {
                Text(text = "Log in",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = typography.h6)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline, fontSize = 18.sp)) {
                    append("Reset password")
                }
            },
            color = MaterialTheme.colorScheme.tertiary,
            style = typography.h6,
            modifier = Modifier.clickable { /*resetPassword()*/ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Create a new account")
                }
            },
            color = MaterialTheme.colorScheme.tertiary,
            style = typography.h6,
            modifier = Modifier.clickable { navController.navigate("createAccount") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
