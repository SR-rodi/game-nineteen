package ru.sr.nineteen.gameitem

import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

sealed interface GameMode {
    enum class Game : GameMode {
        Classic, Random,Next
    }

    enum class Training : GameMode {
        First, Second
    }
}