package ru.sr.nineteen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sr.nineteen.domain.NavigationTree

class MainViewModel(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1_500)
            _isLoading.value = false
        }
    }

    fun getStartScreen(): String {
        return if (firebaseAuth.currentUser?.isEmailVerified == true)
            NavigationTree.Menu.name
        else NavigationTree.SignIn.name
    }

}