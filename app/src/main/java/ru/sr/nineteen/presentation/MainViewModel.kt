package ru.sr.nineteen.presentation

import androidx.lifecycle.ViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.GetTokenUseCase
import ru.sr.nineteen.domain.usecase.PutTokenUseCase

class MainViewModel(
    private val tokenUseCase: GetTokenUseCase,
) : ViewModel() {

    fun getStartScreen(): String {
        return if (tokenUseCase.getToken() == null) NavigationTree.SignIn.name else NavigationTree.Menu.name
    }

}