package com.anaumchik.buildyourbody.data.di

import com.anaumchik.buildyourbody.data.utils.PlayerSession
import com.anaumchik.buildyourbody.ui.game.GameFragmentViewModel
import com.anaumchik.buildyourbody.ui.health.HealthFragmentViewModel
import com.anaumchik.buildyourbody.ui.health.HealthRepository
import com.anaumchik.buildyourbody.ui.main.MainFragmentViewModel
import com.anaumchik.buildyourbody.ui.shop.ShopFragmentViewModel
import com.anaumchik.buildyourbody.ui.work.WorkFragmentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val koinModule = module {
    single { PlayerSession() }
    single { HealthRepository(androidApplication()) }
    viewModel { MainFragmentViewModel(get()) }
    viewModel { GameFragmentViewModel(get()) }
    viewModel { WorkFragmentViewModel() }
    viewModel { HealthFragmentViewModel(get()) }
    viewModel { ShopFragmentViewModel() }
}
