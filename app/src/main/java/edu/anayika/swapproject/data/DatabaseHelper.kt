package edu.anayika.swapproject.data

import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DatabaseHelper {

        private val db = Firebase.firestore
        private val collectionUsers = "users"


        fun createUser(user: User) {
            db.collection(collectionUsers).add(user)
        }

        fun updateUser() {}

        fun readUsers() {}

        fun readUser(email: String): Task<QuerySnapshot> {
            return db.collection(collectionUsers).whereEqualTo("email", email).get()
        }

        fun deleteUser() {}

        companion object {
            private val db = Firebase.firestore
            private const val collectionHouses = "houses"
        fun createHouse(house: House) {
            db.collection("houses").add(house)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "Chalet added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding chalet", e)
                }
        }

        fun updateHouse() {}

        fun readHouses() {}

        fun readHouse() {}

        fun deleteHouse() {}
    }
}