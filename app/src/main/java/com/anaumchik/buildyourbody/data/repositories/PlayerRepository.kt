package com.anaumchik.buildyourbody.data.repositories

import android.app.Application
import com.anaumchik.buildyourbody.data.db.AppDatabase
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.utils.Logger.Companion.log
import com.anaumchik.buildyourbody.data.utils.SessionManager
import com.anaumchik.buildyourbody.data.utils.SessionManager.Companion.MULTIPLIER_EXPERIENCE
import com.anaumchik.buildyourbody.data.utils.SessionManager.Companion.MULTIPLIER_HEALTH
import com.anaumchik.buildyourbody.data.utils.SessionManager.Companion.MULTIPLIER_TIME

class PlayerRepository(
    application: Application,
    private val sessionManager: SessionManager
) {
    private val db: AppDatabase = AppDatabase.getDb(application)

    suspend fun getPlayer(): Player = db.playerDao().getPlayer()

    suspend fun createPlayer(): Player {
        log("PlayerRepository.createPlayer()")
        val defaultPlayer = Player()
        updatePlayer(defaultPlayer)
        return getPlayer()
    }

    suspend fun updatePlayer(player: Player) {
        db.playerDao().updatePlayer(player)
        sessionManager.player = player
        log("PlayerRepository.updatePlayer(): $player")
    }

    suspend fun levelUp(player: Player) {
        player.lvl++

        player.maxExperience = (player.maxExperience * MULTIPLIER_EXPERIENCE).toInt()
        player.experience = 0

        player.maxTime = (player.maxTime * MULTIPLIER_TIME).toInt()
        player.time = player.maxTime

        player.maxHealth = (player.maxHealth * MULTIPLIER_HEALTH).toInt()
        player.health = player.maxHealth * player.maxHealth

        updatePlayer(player)

        log(player.toString())
    }
}
