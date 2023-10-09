package dev.alexandro45.fitnessapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trainer(
    @SerialName("id") val id: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("name") val name: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("position") val position: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("image_url_small") val imageUrlSmall: String,
    @SerialName("image_url_medium") val imageUrlMedium: String,
    @SerialName("description") val description: String
)