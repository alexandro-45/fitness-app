package dev.alexandro45.fitnessapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("place") val place: String,
    @SerialName("coach_id") val coachId: String,
    @SerialName("startTime") val startTime: String,
    @SerialName("endTime") val endTime: String,
    @SerialName("date") val date: String,
    @SerialName("appointment_id") val appointmentId: String,
    @SerialName("service_id") val serviceId: String,
    @SerialName("available_slots") val availableSlots: Int,
    @SerialName("commercial") val commercial: Boolean,
    @SerialName("client_recorded") val clientRecorded: Boolean,
    @SerialName("is_cancelled") val isCancelled: Boolean,
    @SerialName("tab") val tab: String,
    @SerialName("color") val color: String,
    @SerialName("tab_id") val tabId: Int
)
