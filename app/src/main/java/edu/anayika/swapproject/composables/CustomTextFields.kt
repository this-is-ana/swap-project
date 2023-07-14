package edu.anayika.swapproject.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import edu.anayika.swapproject.utils.ClickOutsideToDismissKeyboard


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldWithLabel(label: String, value: String, onValueChange: (String) -> Unit) {
    ClickOutsideToDismissKeyboard {
        Column {
            Text(text = label)
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                label = { Text(text = label) }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberTextFieldWithLabel(label: String, value: String, onValueChange: (String) -> Unit) {
    ClickOutsideToDismissKeyboard {
        Column {
            Text(text = label)
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                label = { Text(text = label) }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextFieldWithLabel(label: String, value: String, onValueChange: (String) -> Unit) {
    ClickOutsideToDismissKeyboard {
        Column {
            Text(text = label)
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                label = { Text(text = label) }
            )
        }
    }
}

