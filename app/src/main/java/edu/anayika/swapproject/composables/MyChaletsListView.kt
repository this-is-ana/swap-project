package edu.anayika.swapproject.composables

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import edu.anayika.swapproject.activities.NewChaletActivity

@Composable
fun MyChaletsListView(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier) {
        Box {
            Text(text = "MyChaletsListView")
        }

        Button(
            onClick = {
                val intent = Intent(context, NewChaletActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text(text = "Add a Chalet")
        }
    }
}

@Preview(name = "MyChaletsListView")
@Composable
private fun PreviewMyChaletsListView() {
    MyChaletsListView()
}
