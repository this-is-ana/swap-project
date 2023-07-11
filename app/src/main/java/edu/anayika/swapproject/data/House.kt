package edu.anayika.swapproject.data

class House(
    var capacity: Int,
    var features: Features,
    var amenities: Amenities,
    var description: String,
    var mainImage: String,
    var images: Array<String>,
    var status: HouseStatus,
    var address: Address,
    var ownerId: String
)