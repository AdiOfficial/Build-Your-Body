package com.anaumchik.buildyourbody.data.utils

import com.anaumchik.buildyourbody.data.entity.Player

class SessionManager {
    lateinit var player: Player

    companion object {
        const val MULTIPLIER_EXPERIENCE = 1.6
        const val MULTIPLIER_HEALTH = 1.2
        const val MULTIPLIER_TIME = 1.4
    }
}
