package com.anaumchik.buildyourbody.data.di

import com.anaumchik.buildyourbody.ui.main.MainActivityViewModel
import com.anaumchik.buildyourbody.ui.main.MainFragmentViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val koinModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { MainFragmentViewModel() }
}
