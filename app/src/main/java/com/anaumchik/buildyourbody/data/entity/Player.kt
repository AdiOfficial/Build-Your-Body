package com.anaumchik.buildyourbody.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class Player(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var health: Int = 100,
    var money: Double = 0.0,
    var daysInGame: Int = 1,
    var time: Int = 25,
    var maxTime: Int = 25,
    var experience: Int = 0,
    var maxExperience: Int = 10,
    var lvl: Int = 1
)
