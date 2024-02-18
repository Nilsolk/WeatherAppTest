package ru.nilsolk.weatherapptest.data_source.cache_data_source.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.nilsolk.weatherapptest.data_source.cache_data_source.Location

interface LocationDAO {
    @Query("SELECT * FROM locations")
    fun getAllLocation(): Flow<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: Location)

    @Query("SELECT * FROM locations WHERE name = :name")
    fun getLocation(name: String): Flow<Location>

    @Query("DELETE FROM locations")
    fun deleteAll()

}