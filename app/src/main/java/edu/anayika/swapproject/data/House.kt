package edu.anayika.swapproject.data

class House(
    var size: String,
    var features: Array<String>,
    var amenities: Array<String>,
    var description: String,
    var mainImage: String,
    var images: Array<String>,
    var status: HouseStatus,
    var address: Address,
    var ownerId: String
)