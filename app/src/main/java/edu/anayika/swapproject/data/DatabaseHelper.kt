package edu.anayika.swapproject.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DatabaseHelper {
    private val db = Firebase.firestore

    fun createUser(user: User) {
        db.collection("users").add(user)
    }

    fun updateUser() {}

    fun readUsers() {}

    fun readUser() {}

    fun deleteUser() {}

    fun createHouse(house: House) {
        db.collection("houses").add(house)
    }

    fun updateHouse() {}

    fun readHouses() {}

    fun readHouse() {}

    fun deleteHouse() {}
}