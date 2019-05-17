package com.anaumchik.buildyourbody.ui.game

import androidx.lifecycle.*
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.utils.Logger.Companion.log
import com.anaumchik.buildyourbody.data.utils.SessionManager

class GameFragmentViewModel(
    private val sessionManager: SessionManager
) : ViewModel(), LifecycleObserver {
    val updateGameProgress = MutableLiveData<Player>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeGame() {
        log("GameFragmentViewModel.onResumeGame: $sessionManager.player")
        updateGameProgress.postValue(sessionManager.player)
    }

    fun onInitLifecycle(lifecycle: Lifecycle) = lifecycle.addObserver(this)
}
