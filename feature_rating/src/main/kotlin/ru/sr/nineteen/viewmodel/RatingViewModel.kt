package ru.sr.nineteen.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sr.mimeteen.database.repository.RatingRepository
import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.database.entity.RatingEntity

class RatingViewModel(
    private val ratingRepository: RatingRepository,
    private val ratingApi: RatingApi,
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

        viewModelScope.launch  {
            Log.e("Kart","rating = ${  ratingApi.getRatingByUseID("1234")}")

            val test = ratingApi.getAllRating().map {
                RatingEntity(it.mode, it.time.toInt(), it.step, 125)
            }
            viewState = viewState
                .copy(ratingItems = test /*ratingRepository.getRatingList().sortedBy { it.time }*/)
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