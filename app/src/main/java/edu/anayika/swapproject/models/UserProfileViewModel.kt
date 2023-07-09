package edu.anayika.swapproject.models

import com.google.firebase.firestore.FirebaseFirestore
import edu.anayika.swapproject.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserProfileViewModel {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _userProfile: MutableStateFlow<User?> = MutableStateFlow(null)
    val userProfile: StateFlow<User?> = _userProfile

    fun fetchUserProfile() {
        firestore.collection("users")
            .document("user_id") // Replace with the actual document ID for the user
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val userProfile = documentSnapshot.toObject(User::class.java)
                _userProfile.value = userProfile
            }
            .addOnFailureListener { exception ->
                // Handle the failure
            }
    }
}