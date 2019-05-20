package com.anaumchik.buildyourbody.data.entity

data class Health(
    val id: Int,
    val icon: Int,
    val title: String,
    val description: String,
    val adjustHealth: Int,
    val costMoney: Int,
    val minLvl: Int,
    val experience: Int
)

data class UpdateHealth(
    val adjustHealth: Int,
    val costMoney: Int,
    val experience: Int
)

fun Health.createUpdateHealth() = UpdateHealth(this.adjustHealth, this.costMoney, this.experience)
