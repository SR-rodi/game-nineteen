package ru.sr.nineteen.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.sr.nineteen.data.FirebaseNotAuth
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.GetTokenUseCase

class MainViewModel(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    fun getStartScreen(): String {
        return if (firebaseAuth.currentUser == null) NavigationTree.SignIn.name else NavigationTree.Menu.name
    }

}