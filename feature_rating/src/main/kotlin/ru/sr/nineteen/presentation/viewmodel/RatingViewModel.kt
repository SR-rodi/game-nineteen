package ru.sr.nineteen.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sr.mimeteen.remotedatabase.FirebaseNotAuth
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.database.entity.RatingEntity
import ru.sr.nineteen.data.mapper.RatingUiMapper
import ru.sr.nineteen.domain.repositoty.RatingRemoteRepository
import ru.sr.nineteen.presentation.model.RatingUIModel

class RatingViewModel(
    private val ratingRepository: RatingRemoteRepository,
    private val ratingUiMapper: RatingUiMapper,
) : BaseViewModel<RatingState, RatingAction, RatingEvent>(RatingState()) {
    override fun obtainEvent(viewEvent: RatingEvent) {

        when (viewEvent) {
            RatingEvent.OnStartScreen -> getRating()
            RatingEvent.OnClickBackStack -> goToBackStack()
            RatingEvent.OnResetAction -> onResetAction()
            RatingEvent.OnClickShowMyResultButton -> getMyRating()
        }
    }

    private fun getMyRating() {
        scopeLaunch(
            onLoading = { viewState = viewState.copy(isMyResultLoading = true, isError = false) },
            onSuccess = { viewState = viewState.copy(isMyResultLoading = false, isError = false) },
            onError = ::onError
        ) {
            val listRating = ratingRepository.showMyRating()
            if (listRating != null) {
                viewState = viewState.copy(
                    myRating = ratingUiMapper.ratingDomainToUI(listRating.last()),
                    myPosition = listRating.lastIndex
                )
            }

        }
    }

    private fun onError(e:Exception){
        if (e is FirebaseNotAuth) viewAction = RatingAction.OpenSignIn
        viewState = viewState.copy(isMyResultLoading = false, isError = true)
    }

    private fun goToBackStack() {
        viewAction = RatingAction.GoToBack
    }

    private fun getRating() {
        scopeLaunch(
            onLoading = { viewState = viewState.copy(isLoading = true, isError = false) },
            onSuccess = { viewState = viewState.copy(isLoading = false, isError = false) },
            onError = { _ -> viewState = viewState.copy(isLoading = false, isError = true) }
        ) {
            val rating = ratingRepository.getTopTenRating().map { rating ->
                ratingUiMapper.ratingDomainToUI(rating)
            }
            viewState = viewState.copy(ratingItems = rating)
        }
    }
}


sealed interface RatingAction {
    object GoToBack : RatingAction
    object OpenSignIn : RatingAction

}

sealed interface RatingEvent {
    object OnStartScreen : RatingEvent
    object OnClickBackStack : RatingEvent
    object OnResetAction : RatingEvent
    object OnClickShowMyResultButton : RatingEvent

}

data class RatingState(
    val isLoading: Boolean = false,
    val isMyResultLoading: Boolean = false,
    val isError: Boolean = false,
    val ratingItems: List<RatingUIModel> = emptyList(),
    val myRating: RatingUIModel? = null,
    val myPosition: Int? = null,
)