package edu.anayika.swapproject.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavController
import edu.anayika.swapproject.data.DatabaseHelper
import edu.anayika.swapproject.data.User
import edu.anayika.swapproject.data.UserType
import edu.anayika.swapproject.models.Authentication

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClickOutsideToDismissKeyboard(
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val outsideClickHandler: () -> Unit = remember {
        {
            focusManager.clearFocus()
            if (keyboardController != null) {
                keyboardController.hide()
            }
        }
    }

    Box(
        modifier = Modifier.clickable { outsideClickHandler.invoke() }
    ) {
        content()
    }
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return emailRegex.matches(email)
}

fun showErrorMessage(errMsg: String, context: Context) {
    Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
}

fun isUserInputsValid(userInputs: User, navController: NavController, context: Context): Boolean {
    return ((isValidEmail(userInputs.email)) && (isValidPassword(userInputs.password)))
}