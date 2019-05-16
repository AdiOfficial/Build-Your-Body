package com.anaumchik.buildyourbody.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.utils.bottomNavMenuRouteTo
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_game.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {
    private val viewModel: GameFragmentViewModel by viewModel()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_work -> bottomNavMenuRouteTo(R.id.work_dest)
            R.id.navigation_health -> bottomNavMenuRouteTo(R.id.health_dest)
            R.id.navigation_shop -> bottomNavMenuRouteTo(R.id.shop_dest)
        }
        false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_game, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
