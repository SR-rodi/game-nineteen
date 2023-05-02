package ru.sr.nineteen.data.repository

import ru.sr.nineteen.data.database.dao.GameDao
import ru.sr.nineteen.data.database.entity.GameListEntity

class GameRepository(
    private val gameDao: GameDao
) {

    fun getGameList() = gameDao.getAll()

    fun insertItemList(itemList: GameListEntity) = gameDao.insert(itemList)

    fun deleteItemList() = gameDao.deleteAll()
}