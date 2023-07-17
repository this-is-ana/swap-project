package edu.anayika.swapproject.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.anayika.swapproject.activities.AccountChaletList
import edu.anayika.swapproject.activities.AddNewChalet
import edu.anayika.swapproject.activities.ChaletDetails
import edu.anayika.swapproject.activities.ContactUs
import edu.anayika.swapproject.activities.CreateAccount
import edu.anayika.swapproject.activities.SearchChalets
import edu.anayika.swapproject.activities.UserProfileAccount
import edu.anayika.swapproject.models.UserProfileViewModel
import edu.anayika.swapproject.activities.UserSession

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination: String = "login",
               viewModel: UserProfileViewModel
) {
    NavHost(modifier = Modifier, navController = navController,
        startDestination = startDestination) {
        composable("login") { Login(navController) }
        composable("createAccount") { CreateAccount(navController) }
        composable("userSession") { UserSession(navController) }
        composable("userProfileAccount") { UserProfileAccount(navController, viewModel) }
        composable("userChaletList") { AccountChaletList(navController) }
        composable("addNewChalet") { AddNewChalet(navController) }
        composable("searchChalets") { SearchChalets(navController) }
        composable("contactUs") { ContactUs(navController) }
        composable("chaletSoloView/{houseId}") {
            val houseId = it.arguments?.getString("houseId")
            if (houseId != null) {
                ChaletDetails(navController, houseId = houseId)
            }
        }
    }
}
