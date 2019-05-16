package com.anaumchik.buildyourbody.data.entity

data class Health(
    val id: Int,
    val icon: Int,
    val title: String,
    val description: String,
    val healthPoint: Int,
    val cost: Int,
    val minLvl: Int
)
