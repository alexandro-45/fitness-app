package dev.alexandro45.fitnessapp.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alexandro45.fitnessapp.network.NetworkClubDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNetworkClubDataSource(networkClubDataSource: NetworkClubDataSource): ClubDataSource
}