package com.anaumchik.buildyourbody.data.repositories

import android.app.Application
import com.anaumchik.buildyourbody.data.db.AppDatabase
import com.anaumchik.buildyourbody.data.entity.Player

class PlayerRepository(application: Application) {
    private val db: AppDatabase = AppDatabase.getDb(application)

    suspend fun getPlayer(): Player = db.playerDao().getPlayer()

    suspend fun createPlayer(): Player {
        val defaultPlayer = Player()
        updatePlayer(defaultPlayer)
        return getPlayer()
    }

    suspend fun updatePlayer(player: Player) = db.playerDao().updatePlayer(player)
}
