package com.anaumchik.buildyourbody.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.utils.SessionManager

class GameFragmentViewModel(sessionManager: SessionManager) : ViewModel() {
    val updateGameProgress = MutableLiveData<Player>()

    init {
        updateGameProgress.postValue(sessionManager.player)
    }
}
