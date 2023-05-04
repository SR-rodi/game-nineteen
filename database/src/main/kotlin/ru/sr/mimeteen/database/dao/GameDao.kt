package ru.sr.mimeteen.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.sr.mimeteen.database.entity.GameListEntity

@Dao
interface GameDao {

    @Query("SELECT*FROM game_list")
    fun getAll(): Flow<GameListEntity?>

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameListEntity: GameListEntity)*/

    @Query("DELETE FROM game_list")
    fun deleteAll()
}
