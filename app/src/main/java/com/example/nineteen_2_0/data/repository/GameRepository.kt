package com.example.nineteen_2_0.data.repository

import com.example.nineteen_2_0.data.database.dao.GameDao
import com.example.nineteen_2_0.data.database.entity.GameListEntity
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameDao: GameDao
) {

    fun getGameList() = gameDao.getAll()

    fun insertItemList(itemList: GameListEntity) = gameDao.insert(itemList)

    fun deleteItemList() = gameDao.deleteAll()
}