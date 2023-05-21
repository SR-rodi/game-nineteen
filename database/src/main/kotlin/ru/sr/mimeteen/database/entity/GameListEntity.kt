package ru.sr.mimeteen.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.SettingGame

@Entity(tableName = "game_list")
class GameListEntity(
    @ColumnInfo(name = "gameMode")
    val gameMode:GameMode.Game,
    @ColumnInfo(name = "list")
    val list :List<List<GameItemEngine>> = listOf(),
    @ColumnInfo(name = "time")
    val time:Long = 0,
    @ColumnInfo(name = "stepCount")
    val stepCount:Int = 1,
    @PrimaryKey
    @ColumnInfo(name = "position")
    val id: Int? = null
) {
    fun toSettingGame() = SettingGame(gameMode, list, time, stepCount)
}