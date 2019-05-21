package com.anaumchik.buildyourbody.ui.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.Player
import com.anaumchik.buildyourbody.data.utils.*
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
        enableToolbarBackButton(true)
        toolbarTitle(R.string.app_name)
        bottom_nav_view.deselectItems()
        bottom_nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        viewModel.onInitLifecycle(lifecycle)
        observeViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.updateGameProgress.observe(this, Observer { updateGameProgress(it) })
    }

    private fun updateGameProgress(player: Player) {
        tvPlayerName.text = string(R.string.player_name, player.name)
        tvPlayerLvl.text = string(R.string.player_lvl, player.lvl)
        tvMoney.text = string(R.string.player_money, player.money)
        tvDaysInGame.text = string(R.string.player_days_in_game, player.daysInGame)

        pbHealth.max = player.maxHealth
        pbHealth.progress = player.health

        pbTime.max = player.maxTime
        pbTime.progress = player.time

        pbExperience.max = player.maxExperience
        pbExperience.progress = player.experience

    }
}
