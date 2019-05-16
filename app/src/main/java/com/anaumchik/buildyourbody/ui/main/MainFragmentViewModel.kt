package com.anaumchik.buildyourbody.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.utils.PlayerSession

class MainFragmentViewModel(private val playerSession: PlayerSession) : ViewModel() {
    val enableStartBtn = MutableLiveData<Unit>()

    init {
        initPlayer()
    }

    private fun initPlayer() {
        playerSession.player = Player()
        enableStartBtn.postValue(Unit)
    }
}
