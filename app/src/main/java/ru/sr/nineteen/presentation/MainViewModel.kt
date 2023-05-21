package ru.sr.nineteen.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.sr.nineteen.domain.NavigationTree

class MainViewModel(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    fun getStartScreen(): String {
        return if (firebaseAuth.currentUser?.isEmailVerified == true) NavigationTree.Menu.name else NavigationTree.SignIn.name
    }

}