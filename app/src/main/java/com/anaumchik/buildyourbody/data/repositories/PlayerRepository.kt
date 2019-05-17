package com.anaumchik.buildyourbody.data.repositories

import android.app.Application
import com.anaumchik.buildyourbody.data.db.AppDatabase
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.utils.Logger.Companion.log
import com.anaumchik.buildyourbody.data.utils.SessionManager

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
}
