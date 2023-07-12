package edu.anayika.swapproject.data

import android.content.Context
import androidx.navigation.NavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.anayika.swapproject.utils.showErrorMessage

class DatabaseHelper {
    private val db = Firebase.firestore
    private val collectionUsers = "users"

    fun createUser(user: User, password: String, navController: NavController, context: Context) {
        readUser(user.email)
            .addOnSuccessListener { results ->
                if(results.isEmpty) {
                    db.collection(collectionUsers).add(user).addOnSuccessListener {
                        Firebase.auth.createUserWithEmailAndPassword(user.email, password).addOnSuccessListener {
                            navController.navigate("userSession")
                        }
                    }
                } else {
                    val errMsg = "Ce courriel exite déjà. Veuillez vous connecter."
                    showErrorMessage(errMsg, context)
                }
            }
    }

    fun updateUser() {}

    fun readUsers() {}

    fun readUser(email: String): Task<QuerySnapshot> {
        return db.collection(collectionUsers).whereEqualTo("email", email).get()
    }

    fun deleteUser() {}

    fun createHouse(house: House) {
        db.collection("houses").add(house)
    }

    fun updateHouse() {}

    fun readHouses() {}

    fun readHouse() {}

    fun deleteHouse() {}
}