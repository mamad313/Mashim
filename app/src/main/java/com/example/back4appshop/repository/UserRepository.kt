package com.example.back4appshop.repository

import androidx.lifecycle.LiveData
import com.example.back4appshop.data.BasketDao
import com.example.back4appshop.model.User


class UserRepository(private val basketDao: BasketDao) {

    val readAllData: LiveData<List<User>> = basketDao.readData()

    suspend fun addUser(user: User){
        basketDao.addBasket(user)
    }

    suspend fun updateUser(user: User){
        basketDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        basketDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        basketDao.deleteAllUser()
    }

}