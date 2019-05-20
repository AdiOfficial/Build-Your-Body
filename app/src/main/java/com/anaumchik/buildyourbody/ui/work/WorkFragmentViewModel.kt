package com.anaumchik.buildyourbody.ui.work

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.entity.UpdateWork
import com.anaumchik.buildyourbody.data.entity.Work
import com.anaumchik.buildyourbody.data.repositories.PlayerRepository
import com.anaumchik.buildyourbody.data.utils.SessionManager.Companion.MULTIPLIER_EXPERIENCE
import com.anaumchik.buildyourbody.data.utils.SessionManager.Companion.MULTIPLIER_HEALTH
import com.anaumchik.buildyourbody.data.utils.SessionManager.Companion.MULTIPLIER_TIME
import com.anaumchik.buildyourbody.data.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WorkFragmentViewModel(
    workRepository: WorkRepository,
    private val playerRepository: PlayerRepository
) : ViewModel() {
    val updateAdapter = MutableLiveData<List<Work>>()
    val finish = SingleLiveEvent<Unit>()

    init {
        val items = workRepository.getItems()
        updateAdapter.postValue(items)
    }

    fun onAdapterItemClick(update: UpdateWork) = getPlayer(update)

    private fun getPlayer(update: UpdateWork) = viewModelScope.launch(Dispatchers.IO) {
        val deferredPlayer = async(Dispatchers.IO) { playerRepository.getPlayer() }
        val player = deferredPlayer.await()
        updatePlayer(update, player)
    }

    private fun updatePlayer(update: UpdateWork, player: Player) = viewModelScope.launch(Dispatchers.IO) {
        player.money += update.adjustMoney
        player.health -= update.costHealth
        player.time -= 1
        player.daysInGame += 1
        player.experience += update.experience

        if ((player.experience + update.experience) >= player.maxExperience) levelUp(player)

        playerRepository.updatePlayer(player)

        finishScreen()
    }

    private fun levelUp(player: Player) {
        player.lvl++

        player.experience = 0
        player.maxExperience *= MULTIPLIER_EXPERIENCE.toInt()

        player.maxTime *= MULTIPLIER_TIME.toInt()
        player.time = player.maxTime

        player.maxHealth *= MULTIPLIER_HEALTH.toInt()
        player.health *= player.maxHealth
    }

    private fun finishScreen() = viewModelScope.launch(Dispatchers.Main) { finish.call() }
}
