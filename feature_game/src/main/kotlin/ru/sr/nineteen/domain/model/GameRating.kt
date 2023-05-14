package ru.sr.nineteen.domain.model

import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.gameitem.GameMode

class GameRating(
    val steps: Int,
    val gameMode: String,
    val time: Long,
)