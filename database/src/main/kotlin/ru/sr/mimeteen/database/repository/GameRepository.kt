package ru.sr.mimeteen.database.repository

import ru.sr.mimeteen.database.dao.GameDao
import ru.sr.mimeteen.database.entity.GameListEntity

class GameRepository(
    private val gameDao: GameDao
) {

    fun getGameList() = gameDao.getAll()

   // fun insertItemList(itemList: GameListEntity) = gameDao.insert(itemList)

    fun deleteItemList() = gameDao.deleteAll()
}