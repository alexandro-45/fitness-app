package dev.alexandro45.fitnessapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tab(
    @SerialName("id") val id: Int, @SerialName("name") val name: String
)
