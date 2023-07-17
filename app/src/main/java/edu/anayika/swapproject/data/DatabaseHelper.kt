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
import kotlinx.coroutines.tasks.await

class DatabaseHelper {
    private val db = Firebase.firestore
    private val collectionUsers = "users"
    private val collectionHouses = "houses"

    fun createUser(user: User, password: String, navController: NavController, context: Context) {
        readUserByEmail(user.email).addOnSuccessListener { results ->
            if (results != null) {
                if(results.isEmpty) {
                    db.collection(collectionUsers).add(user).addOnSuccessListener {
                        Firebase.auth.createUserWithEmailAndPassword(user.email, password).addOnSuccessListener {
                            navController.navigate("userSession")
                        }
                    }
                } else {
                    val errMsg = "Ce courriel existe déjà. Veuillez vous connecter."
                    showErrorMessage(errMsg, context)
                }
            }
        }
    }

    fun updateUser(user: User, docId: String) {
        db.collection(collectionUsers).document(docId).set(user)
    }

    fun readUserByEmail(email: String): Task<QuerySnapshot> {
        return db.collection(collectionUsers).whereEqualTo("email", email).get()
    }

    suspend fun readUserIdByEmail(email: String): String {
        val user = db.collection(collectionUsers).whereEqualTo("email", email).get().await()
        var userId = ""

        for(el in user) {
            userId = el.id
        }

        return userId
    }

    fun deleteUser(docId: String) {
        db.collection(collectionUsers).document(docId).delete()
    }

    fun createHouse(house: HashMap<String, Any>, features: Features, amenities: Amenities, address: Address) {
        db.collection(collectionHouses).add(house).addOnSuccessListener { result ->
            db.collection(collectionHouses).document(result.id).collection("features").add(features)
            db.collection(collectionHouses).document(result.id).collection("amenities").add(amenities)
            db.collection(collectionHouses).document(result.id).collection("address").add(address)
        }
    }

    suspend fun readHouse(houseId: String): House {
        val documentSnapshot = db.collection(collectionHouses).document(houseId).get().await()
        val documentData = documentSnapshot.data!!

        return House(
            documentData["capacity"].toString().toInt(),
            getFeatures(documentSnapshot),
            getAmenities(documentSnapshot),
            documentData["title"].toString(),
            documentData["shortDescription"].toString(),
            documentData["description"].toString(),
            documentData["mainImage"].toString(),
            documentData["images"].toString(),
            enumValueOf(documentData["status"].toString()),
            getAddress(documentSnapshot),
            documentData["ownerId"].toString()
        )
    }

    suspend fun readHouses(ownerId: String): ArrayList<House> {
        val chalets = ArrayList<House>()
        val documentSnapshots = db.collection(collectionHouses).whereNotEqualTo("ownerId", ownerId).get().await()

        for (documentSnapshot in documentSnapshots.documents) {
            val documentData = documentSnapshot.data!!
            val chalet = House(
                documentData["capacity"].toString().toInt(),
                getFeatures(documentSnapshot),
                getAmenities(documentSnapshot),
                documentData["title"].toString(),
                documentData["shortDescription"].toString(),
                documentData["description"].toString(),
                documentData["mainImage"].toString(),
                documentData["images"].toString(),
                enumValueOf(documentData["status"].toString()),
                getAddress(documentSnapshot),
                documentData["ownerId"].toString()
            )

            chalet.documentId = documentSnapshot.id

            chalets.add(chalet)
        }

        return chalets
    }

    suspend fun readHousesByOwner(ownerId: String): ArrayList<House> {
        val chalets = ArrayList<House>()

        val documentSnapshots = db.collection(collectionHouses).whereEqualTo("ownerId", ownerId).get().await()

        for (documentSnapshot in documentSnapshots.documents) {
            val documentData = documentSnapshot.data!!
            val chalet = House(
                documentData["capacity"].toString().toInt(),
                getFeatures(documentSnapshot),
                getAmenities(documentSnapshot),
                documentData["title"].toString(),
                documentData["shortDescription"].toString(),
                documentData["description"].toString(),
                documentData["mainImage"].toString(),
                documentData["images"].toString(),
                enumValueOf(documentData["status"].toString()),
                getAddress(documentSnapshot),
                documentData["ownerId"].toString()
            )

            chalet.documentId = documentSnapshot.id

            chalets.add(chalet)
        }

        return chalets
    }

    fun updateHouse(house: HashMap<String, Any>, features: Features, amenities: Amenities, address: Address, docId: String) {

        db.collection(collectionHouses).document(docId).set(house)

        getSubCollection(docId, "features").addOnSuccessListener { resFeatures ->
            var docIdFeatures = ""

            for(res in resFeatures) {
                docIdFeatures = res.id
            }

            db.collection(collectionHouses).document(docId).collection("features").document(docIdFeatures).set(features)
        }

        getSubCollection(docId, "amenities").addOnSuccessListener { resAmenities ->
            var docIdAmenities = ""

            for(res in resAmenities) {
                docIdAmenities = res.id
            }

            db.collection(collectionHouses).document(docId).collection("amenities").document(docIdAmenities).set(amenities)
        }

        getSubCollection(docId, "address").addOnSuccessListener { resAddress ->
            var docIdAddress = ""

            for(res in resAddress) {
                docIdAddress = res.id
            }
            db.collection(collectionHouses).document(docId).collection("address").document(docIdAddress).set(address)
        }
    }

    fun deleteHouse() {}

    private suspend fun getAmenities(documentSnapshot: DocumentSnapshot) : Amenities {
        lateinit var amenities: Amenities

        val resAmenities = readSubCollection(documentSnapshot.id, "amenities")

        if (resAmenities != null) {
            for (amenitiesDoc in resAmenities.documents) {
                val resultData = amenitiesDoc.data!!

                amenities = Amenities(
                    resultData["bedroomsQty"].toString().toInt(),
                    resultData["bedsQty"].toString().toInt(),
                    resultData["washroomsQty"].toString().toInt(),
                    resultData["balcony"].toString().toBoolean(),
                    resultData["spa"].toString().toBoolean(),
                    resultData["piscine"].toString().toBoolean(),
                    resultData["fireplace"].toString().toBoolean(),
                    resultData["internet"].toString().toBoolean(),
                    resultData["television"].toString().toBoolean(),
                    resultData["climatisation"].toString().toBoolean(),
                    resultData["bbq"].toString().toBoolean(),
                    resultData["logsChalet"].toString().toBoolean()
                )
            }
        }

        return amenities
    }

    private suspend fun getFeatures(documentSnapshot: DocumentSnapshot) : Features {

        lateinit var features: Features

        val resFeatures = readSubCollection(documentSnapshot.id, "features")

        if (resFeatures != null) {
            for (featuresDoc in resFeatures.documents) {
                val docData = featuresDoc.data!!

                features = Features(
                    docData["fishing"].toString().toBoolean(),
                    docData["waterfront"].toString().toBoolean(),
                    docData["waterAccess"].toString().toBoolean(),
                    docData["woodedArea"].toString().toBoolean(),
                    docData["smokersAllowed"].toString().toBoolean(),
                    docData["petsAllowed"].toString().toBoolean()
                )
            }
        }

        return features
    }

    private suspend fun getAddress(documentSnapshot: DocumentSnapshot) : Address {
        lateinit var address: Address

        val resAddress = readSubCollection(documentSnapshot.id, "address")

        if (resAddress != null) {
            for (addressDoc in resAddress.documents) {
                val docData = addressDoc.data!!

                address = Address(
                    docData["address1"].toString(),
                    docData["address2"].toString(),
                    docData["city"].toString(),
                    docData["province"].toString(),
                    docData["postalCode"].toString(),
                    docData["country"].toString()
                )
            }
        }

        return address
    }

    private suspend fun readSubCollection(documentId: String, collectionName: String): QuerySnapshot? {
        return db.collection(collectionHouses).document(documentId).collection(collectionName).get().await()
    }

    private fun getSubCollection(documentId: String, collectionName: String): Task<QuerySnapshot> {
        return db.collection(collectionHouses).document(documentId).collection(collectionName).get()
    }
}