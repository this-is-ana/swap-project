package edu.anayika.swapproject.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authentication {
    private var auth: FirebaseAuth = Firebase.auth

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    fun signIn(email: String, password: String) : FirebaseUser? {
        auth.signInWithEmailAndPassword(email, password)

        Thread.sleep(1000)

        return auth.currentUser
    }

    fun getCurrentUser() : FirebaseUser? {
        return auth.currentUser
    }
}