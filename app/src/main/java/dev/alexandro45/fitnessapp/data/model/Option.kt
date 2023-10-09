package dev.alexandro45.fitnessapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    @SerialName("club_name") val clubName: String,
    @SerialName("link_android") val linkAndroid: String,
    @SerialName("link_ios") val linkIos: String,
    @SerialName("primary_color") val primaryColor: String,
    @SerialName("secondary_color") val secondaryColor: String
)
