package com.anaumchik.buildyourbody

import android.app.Application
import com.anaumchik.buildyourbody.data.di.koinModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(koinModule))
    }
}
