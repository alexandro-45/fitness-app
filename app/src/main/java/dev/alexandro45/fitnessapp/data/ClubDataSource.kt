package dev.alexandro45.fitnessapp.data

import dev.alexandro45.fitnessapp.data.model.Club

interface ClubDataSource {

    suspend fun getClub(clubId: Int): Club
}