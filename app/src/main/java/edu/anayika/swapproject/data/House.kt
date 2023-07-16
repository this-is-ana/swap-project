package edu.anayika.swapproject.data

class House {
    var capacity: Int = 0
    lateinit var features: Features
    lateinit var amenities: Amenities
    var title: String = ""
    var shortDescription: String = ""
    var description: String = ""
    var mainImage: String = ""
    var images: String = ""
    var status: HouseStatus = HouseStatus.AVAILABLE
    lateinit var address: Address
    var ownerId: String = ""

    constructor(){}

    constructor(
        capacity: Int,
        features: Features,
        amenities: Amenities,
        title: String,
        shortDescription: String,
        description: String,
        mainImage: String,
        images: String,
        status: HouseStatus,
        address: Address,
        ownerId: String
    ) {
        this.capacity = capacity
        this.features = features
        this.amenities = amenities
        this.title = title
        this.shortDescription = shortDescription
        this.description = description
        this.mainImage = mainImage
        this.images = images
        this.status = status
        this.address = address
        this.ownerId = ownerId
    }
}