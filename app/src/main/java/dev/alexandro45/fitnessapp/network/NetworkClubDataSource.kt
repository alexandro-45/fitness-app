package dev.alexandro45.fitnessapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.alexandro45.fitnessapp.data.ClubDataSource
import dev.alexandro45.fitnessapp.data.model.Club
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface NetworkApi {

    @GET("schedule/get_v3")
    suspend fun getClub(@Query("club_id") clubId: Int): Club
}

@Singleton
class NetworkClubDataSource @Inject constructor(callFactory: Call.Factory, json: Json) :
    ClubDataSource {

    companion object {
        val BASE_URL = "https://olimpia.fitnesskit-admin.ru/"
    }

    private val networkApi = Retrofit.Builder().baseUrl(BASE_URL).callFactory(callFactory)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
        .create(NetworkApi::class.java)

    override suspend fun getClub(clubId: Int): Club {
        return networkApi.getClub(clubId)
    }
}