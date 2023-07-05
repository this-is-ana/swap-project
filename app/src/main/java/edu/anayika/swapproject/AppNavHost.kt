package edu.anayika.swapproject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination: String = "login") {
    NavHost(modifier = Modifier, navController = navController,
        startDestination = startDestination) {
        composable("login") { Login(navController) }
        composable("createAccount") { CreateAccount(navController)}
        composable("userSession") { UserSession(navController)}
    }
}
