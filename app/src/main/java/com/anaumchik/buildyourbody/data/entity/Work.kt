package com.anaumchik.buildyourbody.data.entity

data class Work(
    val id: Int,
    val icon: Int,
    val title: String,
    val description: String,
    val adjustMoney: Int,
    val costHealth: Int,
    val minLvl: Int,
    val experience: Int
)

data class UpdateWork(
    val adjustMoney: Int,
    val costHealth: Int,
    val experience: Int
)

fun Work.createUpdateWork() = UpdateWork(this.adjustMoney, this.costHealth, this.experience)
