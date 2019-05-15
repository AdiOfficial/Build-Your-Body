package com.anaumchik.buildyourbody.data.di

import com.anaumchik.buildyourbody.ui.game.GameFragmentViewModel
import com.anaumchik.buildyourbody.ui.main.MainFragmentViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val koinModule = module {
    viewModel { MainFragmentViewModel() }
    viewModel { GameFragmentViewModel() }
}
