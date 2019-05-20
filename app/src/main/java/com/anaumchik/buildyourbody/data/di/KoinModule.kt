package com.anaumchik.buildyourbody.data.di

import com.anaumchik.buildyourbody.data.repositories.PlayerRepository
import com.anaumchik.buildyourbody.data.utils.PlayerSharedPrefs
import com.anaumchik.buildyourbody.data.utils.SessionManager
import com.anaumchik.buildyourbody.ui.game.GameFragmentViewModel
import com.anaumchik.buildyourbody.ui.health.HealthFragmentViewModel
import com.anaumchik.buildyourbody.ui.health.HealthRepository
import com.anaumchik.buildyourbody.ui.main.MainFragmentViewModel
import com.anaumchik.buildyourbody.ui.shop.ShopFragmentViewModel
import com.anaumchik.buildyourbody.ui.work.WorkFragmentViewModel
import com.anaumchik.buildyourbody.ui.work.WorkRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val koinModule = module {
    single { PlayerSharedPrefs(androidApplication()) }
    single { SessionManager() }
    single { PlayerRepository(androidApplication(), get()) }
    single { HealthRepository(androidApplication()) }
    single { WorkRepository(androidApplication()) }
    viewModel { MainFragmentViewModel(get(), get(), get()) }
    viewModel { GameFragmentViewModel(get()) }
    viewModel { WorkFragmentViewModel(get(), get()) }
    viewModel { HealthFragmentViewModel(get(), get()) }
    viewModel { ShopFragmentViewModel() }
}
