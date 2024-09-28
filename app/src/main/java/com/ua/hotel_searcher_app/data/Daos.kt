package com.ua.hotel_searcher_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDao {
    @Insert
    suspend fun insert(hotel: HotelEntity)

    @Delete
    suspend fun delete(hotel: HotelEntity)

    @Query("SELECT * FROM wishlist")
    fun getAllHotels(): LiveData<List<HotelEntity>>

    @Query("SELECT * FROM wishlist WHERE title = :title LIMIT 1")
    suspend fun getHotelByTitle(title: String): HotelEntity?
}
