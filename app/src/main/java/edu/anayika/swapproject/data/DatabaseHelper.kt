package edu.anayika.swapproject.data

import android.content.Context
import androidx.navigation.NavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.anayika.swapproject.utils.showErrorMessage

class DatabaseHelper {
    private val db = Firebase.firestore
    private val collectionUsers = "users"
    private val collectionHouses = "houses"

    fun createUser(user: User, password: String, navController: NavController, context: Context) {
        readUserByEmail(user.email)
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

    fun updateUser(user: User, docId: String) {
        db.collection(collectionUsers).document(docId).set(user)
    }

    fun readUser(docId: String) : Task<DocumentSnapshot> {
        return db.collection(collectionUsers).document(docId).get()
    }

    fun readUserByEmail(email: String): Task<QuerySnapshot> {
        return db.collection(collectionUsers).whereEqualTo("email", email).get()
    }

    fun deleteUser(docId: String) {
        db.collection(collectionUsers).document(docId).delete()
    }

    fun createHouse(house: HashMap<String, Any>) {
        db.collection(collectionHouses).add(house)
    }

    fun updateHouse() {}

    fun readHouses() {}

    fun readHouse() {}

    fun deleteHouse() {}
}