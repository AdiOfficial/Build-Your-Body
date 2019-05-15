package com.anaumchik.buildyourbody.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anaumchik.buildyourbody.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
