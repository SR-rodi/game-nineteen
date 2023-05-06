package ru.sr.nineteen.viewmodel

import kotlinx.coroutines.Dispatchers
import ru.sr.mimeteen.database.repository.RatingRepository
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.database.entity.RatingEntity

class RatingViewModel(
    private val ratingRepository: RatingRepository,
) : BaseViewModel<RatingState, RatingAction, RatingEvent>(RatingState()) {
    override fun obtainEvent(viewEvent: RatingEvent) {

        when (viewEvent) {
            RatingEvent.OnStartScreen -> getRating()
            RatingEvent.OnClickBackStack -> goToBackStack()
            RatingEvent.OnResetAction -> onResetAction()
        }
    }

    private fun goToBackStack() {
        viewAction = RatingAction.GoToBack
    }

    private fun getRating() {
        scopeLaunch(context = Dispatchers.IO) {
            viewState = viewState
                .copy(ratingItems = ratingRepository.getRatingList().sortedBy { it.time })
        }
    }
}

sealed interface RatingAction {
    object GoToBack : RatingAction

}

sealed interface RatingEvent {
    object OnStartScreen : RatingEvent
    object OnClickBackStack : RatingEvent
    object OnResetAction : RatingEvent

}

data class RatingState(
    val ratingItems: List<RatingEntity> = emptyList(),
)