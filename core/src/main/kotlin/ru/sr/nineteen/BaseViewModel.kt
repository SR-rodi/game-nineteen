package ru.sr.nineteen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sr.nineteen.domain.gameitem.SettingGame
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State : Any, Action : Any, Event : Any>(initialState: State) :
    ViewModel() {

    private val _viewStates = MutableStateFlow(initialState)
    private val _viewActions =
        MutableSharedFlow<Action?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    fun viewStates() = _viewStates.asStateFlow()
    fun viewActions() = _viewActions.asSharedFlow()

    protected var viewAction: Action?
        get() = _viewActions.replayCache.last()
        set(value) {
            _viewActions.tryEmit(value)
        }

    abstract fun obtainEvent(viewEvent: Event)

    protected fun onResetAction() {
        viewAction = null
    }

    inline fun scopeLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        crossinline onLoading: () -> Unit = {},
        crossinline onSuccess: () -> Unit = {},
        crossinline onError: (t: Exception) -> Unit = {},
        crossinline onFinally: () -> Unit = {},
        crossinline job: suspend () -> Unit,
    ): Job {
        return viewModelScope.launch(context) {
            try {
                onLoading()
                job()
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            } finally {
                onFinally()
            }
        }
    }

}
