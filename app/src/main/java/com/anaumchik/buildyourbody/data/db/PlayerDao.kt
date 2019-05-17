package com.anaumchik.buildyourbody.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anaumchik.buildyourbody.data.entity.Player

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlayer(unit: Player)

    @Query("SELECT * FROM player WHERE id = :id")
    fun getPlayer(id: Int = 0): Player
}
