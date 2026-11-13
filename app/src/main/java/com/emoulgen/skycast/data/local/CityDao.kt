package com.emoulgen.skycast.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Upsert
    suspend fun upsertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM cities ORDER BY lastOpenedAt DESC LIMIT 1")
    fun getLastOpenedCity(): Flow<CityEntity?>

    @Query("SELECT * FROM cities WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE id = :id")
    fun getCity(id: Int): Flow<CityEntity?>

    @Query("UPDATE cities SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(isFavorite: Boolean, id: Int)
}