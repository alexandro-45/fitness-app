package dev.alexandro45.fitnessapp.data

import dev.alexandro45.fitnessapp.data.model.Club
import javax.inject.Inject

class ClubRepository @Inject constructor(private val clubDataSource: ClubDataSource) {

    suspend fun getClub(clubId: Int = 2): Club {
        return clubDataSource.getClub(clubId)
    }
}