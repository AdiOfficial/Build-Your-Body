package com.anaumchik.buildyourbody.ui.health

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaumchik.buildyourbody.data.entity.Health
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.entity.UpdateHealth
import com.anaumchik.buildyourbody.data.repositories.PlayerRepository
import com.anaumchik.buildyourbody.data.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HealthFragmentViewModel(
    healthRepository: HealthRepository,
    private val playerRepository: PlayerRepository
) : ViewModel() {
    val updateAdapter = MutableLiveData<List<Health>>()
    val finish = SingleLiveEvent<Unit>()

    init {
        val items = healthRepository.getItems()
        updateAdapter.postValue(items)
    }

    fun onAdapterItemClick(updateHealth: UpdateHealth) = getPlayer(updateHealth)

    private fun getPlayer(updateHealth: UpdateHealth) = viewModelScope.launch(Dispatchers.IO) {
        val deferredPlayer = async(Dispatchers.IO) { playerRepository.getPlayer() }
        val player = deferredPlayer.await()
        updatePlayer(updateHealth, player)
    }

    private fun updatePlayer(
        updateHealth: UpdateHealth,
        player: Player
    ) = viewModelScope.launch(Dispatchers.IO) {
        player.health += updateHealth.healthPoint
        player.money -= updateHealth.cost
        player.time -= 1
        player.daysInGame += 1
        player.experience += updateHealth.experience

        playerRepository.updatePlayer(player)

        finishScreen()
    }

    private fun finishScreen() = viewModelScope.launch(Dispatchers.Main) { finish.call() }
}
