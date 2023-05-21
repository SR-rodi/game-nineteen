package ru.sr.nineteen.gameitem

sealed interface GameMode {
    enum class Game : GameMode {
        Classic, Random, Next
    }

    enum class Training : GameMode {
        First, Second, Third, Fourth, Fifth
    }
}