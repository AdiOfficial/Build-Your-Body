package com.anaumchik.buildyourbody.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.repositories.PlayerRepository
import com.anaumchik.buildyourbody.data.utils.PlayerSharedPrefs
import com.anaumchik.buildyourbody.data.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val sessionManager: SessionManager,
    private val playerRepository: PlayerRepository,
    private val playerSharedPrefs: PlayerSharedPrefs
) : ViewModel() {
    val enableStartBtn = MutableLiveData<Unit>()

    init {
        restoreSession()
    }

    private fun restoreSession() {
        if (playerSharedPrefs.isInitialLaunch) createPlayer()
        else getPlayer()
    }

    private fun createPlayer() {
        viewModelScope.launch(Dispatchers.IO) {
            val deferredPlayer = async(Dispatchers.IO) { playerRepository.createPlayer() }
            val player = deferredPlayer.await()
            initPlayer(player)
        }
    }

    private fun getPlayer() {
        viewModelScope.launch(Dispatchers.IO) {
            val deferredPlayer = async(Dispatchers.IO) { playerRepository.getPlayer() }
            val player = deferredPlayer.await()
            initPlayer(player)
        }
    }

    private fun initPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) { sessionManager.player = player }
        viewModelScope.launch(Dispatchers.Main) { enableStartBtn.postValue(Unit) }
    }
}
