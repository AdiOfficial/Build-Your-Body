package com.anaumchik.buildyourbody.data.utils

import android.app.Application


class PlayerSharedPrefs(application: Application) : SharedPrefs(application) {

    var isInitialLaunch: Boolean
        get() = getBoolean(IS_INITIAL_LAUNCH, true)
        set(value) {
            put(IS_INITIAL_LAUNCH, value)
        }

    companion object {
        const val PREF_NAME = "build_your_muscle"
        const val IS_INITIAL_LAUNCH = "is_unitial_launch"
    }
}
