package ru.sr.nineteen.domain

enum class NavigationTree(val key: String = "") {
    Game("Game_field"),
    Registration("Registration_emile"),
    ResetPassword("ResetPassword_emile"),
    Menu,
    Training,
    Rating,
    Win("Win_args"),
    SignIn,
    Profile
}