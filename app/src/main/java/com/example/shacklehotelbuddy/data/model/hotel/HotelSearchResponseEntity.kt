package com.example.shacklehotelbuddy.data.model.hotel

import com.google.gson.annotations.SerializedName

data class HotelSearchResponseEntity(
    @SerializedName("data")
    val data: Data?
)

data class Data(
    @SerializedName("propertySearch")
    val propertySearch: PropertySearch?
)

data class PropertySearch(
    @SerializedName("properties")
    val properties: List<Property> = arrayListOf()
)

data class Property(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("propertyImage")
    val propertyImage: PropertyImage?,
    @SerializedName("star")
    val star: String?,
    @SerializedName("price")
    val price: PriceResponse?,
    @SerializedName("neighborhood")
    val neighborhood: Neighborhood?,
    @SerializedName("reviews")
    val reviews: Reviews?
)

data class PropertyImage(
    @SerializedName("image")
    val image: Image?
)
data class Image(
    @SerializedName("url")
    val url: String?
)

data class PriceResponse(
    @SerializedName("options")
    val options: List<Options>? = emptyList()
)

data class Options(
    @SerializedName("formattedDisplayPrice")
    val formattedDisplayPrice: String?
)

data class Neighborhood(
    @SerializedName("name")
    val name: String?
)

data class Reviews(
    @SerializedName("score")
    val score: String?,
    @SerializedName("total")
    val total: String?
)
