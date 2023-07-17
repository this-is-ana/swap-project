package edu.anayika.swapproject.data

import com.google.firebase.firestore.Exclude

class House(
    var capacity: Int,
    var features: Features,
    var amenities: Amenities,
    var title: String,
    var shortDescription: String,
    var description: String,
    var mainImage: String,
    var images: String,
    var status: HouseStatus,
    var address: Address,
    var ownerId: String
) {
    @Exclude
    var documentId:String = ""
}