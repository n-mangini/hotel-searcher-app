package com.ua.innVista.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDao {
    @Insert
    suspend fun insert(hotel: HotelEntity)

    @Query("DELETE FROM hotels WHERE id = :hotelId")
    suspend fun deleteById(hotelId: Long)

    @Query("SELECT * FROM hotels")
    fun getAllHotels(): LiveData<List<HotelEntity>>

    @Query("SELECT * FROM hotels WHERE title = :title LIMIT 1")
    suspend fun getHotelByTitle(title: String): HotelEntity?
}
