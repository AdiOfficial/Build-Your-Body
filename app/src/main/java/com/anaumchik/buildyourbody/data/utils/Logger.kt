package com.anaumchik.buildyourbody.data.utils

import android.util.Log

class Logger {
    companion object {
        private const val LOG_TAG = "anaumchik.game"
        fun log(message: String) = Log.d(LOG_TAG, message)
        fun error(message: String) = Log.e(LOG_TAG, message)
    }
}

