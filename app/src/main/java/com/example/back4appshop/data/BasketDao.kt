package com.example.back4appshop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.back4appshop.model.User


@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBasket(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM table_basket")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM table_basket ORDER BY id ASC")
    fun readData(): LiveData<List<User>>
}