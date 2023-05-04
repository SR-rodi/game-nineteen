package ru.sr.mimeteen.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.SettingGame

@Entity(tableName = "game_list")
class GameListEntity(
    @ColumnInfo(name = "gameMode")
    val gameMode:String = "classic",
    @ColumnInfo(name = "list")
    val list :List<GameItemEngine> = listOf(),
    @ColumnInfo(name = "time")
    val time:Int = 0,
    @ColumnInfo(name = "stepCount")
    val stepCount:Int = 1,
    @PrimaryKey
    @ColumnInfo(name = "position")
    val id: Int? = null
) {
    fun toSettingGame() = SettingGame(gameMode, listOf(),time,stepCount)
}