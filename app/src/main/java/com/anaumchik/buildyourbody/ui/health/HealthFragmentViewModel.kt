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
    val showLevelUpDialog = SingleLiveEvent<Int>()

    init {
        val items = healthRepository.getItems()
        updateAdapter.postValue(items)
    }

    fun onAdapterItemClick(update: UpdateHealth) = getPlayer(update)

    private fun getPlayer(update: UpdateHealth) = viewModelScope.launch(Dispatchers.IO) {
        val deferredPlayer = async(Dispatchers.IO) { playerRepository.getPlayer() }
        val player = deferredPlayer.await()
        updatePlayer(update, player)
    }

    private fun updatePlayer(update: UpdateHealth, player: Player) = viewModelScope.launch(Dispatchers.IO) {
        player.health += update.adjustHealth
        player.money -= update.costMoney
        player.time -= 1
        player.daysInGame += 1
        player.experience += update.experience

        if ((player.health + update.adjustHealth) >= player.maxHealth) player.health = player.maxHealth

        if ((player.experience + update.experience) >= player.maxExperience) {
            playerRepository.levelUp(player)
            showLevelUpDialog(player.lvl)
        } else {
            playerRepository.updatePlayer(player)
            finishScreen()
        }
    }

    private fun showLevelUpDialog(playerLvl: Int) =
        viewModelScope.launch(Dispatchers.Main) { showLevelUpDialog.postValue(playerLvl) }

    fun onFinishScreen() = finishScreen()

    private fun finishScreen() = viewModelScope.launch(Dispatchers.Main) { finish.call() }
}
