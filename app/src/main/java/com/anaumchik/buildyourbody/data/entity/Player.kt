package com.anaumchik.buildyourbody.data.entity

data class Player(
    var id: Int = 1,
    var name: String = "",
    var health: Int = 100,
    var money: Double = 0.0,
    var daysInGame: Int = 0,
    var time: Int = 25,
    var maxTime: Int = 25,
    var experience: Int = 0,
    var maxExperience: Int = 10,
    var lvl: Int = 1
)
