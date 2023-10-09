package dev.alexandro45.fitnessapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Club(
    @SerialName("trainers") val trainers: List<Trainer>,
    @SerialName("tabs") val tabs: List<Tab>,
    @SerialName("lessons") val lessons: List<Lesson>,
    @SerialName("option") val option: Option
)
