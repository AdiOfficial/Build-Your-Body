package com.anaumchik.buildyourbody.ui.health.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.Health
import com.anaumchik.buildyourbody.data.entity.UpdateHealth
import com.anaumchik.buildyourbody.data.entity.createUpdateHealth
import com.anaumchik.buildyourbody.data.utils.SessionManager
import com.anaumchik.buildyourbody.data.utils.background
import com.anaumchik.buildyourbody.data.utils.loadDrawable
import com.anaumchik.buildyourbody.data.utils.toast
import kotlinx.android.synthetic.main.item_health.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class HealthViewHolder(private val view: View) : RecyclerView.ViewHolder(view), KoinComponent {
    private val playerSession = inject<SessionManager>().value

    private lateinit var listener: HealthAdapterListener
    private var imgIcon: ImageView = view.imgIcon
    private var tvTitle: TextView = view.tvTitle
    private var tvDescription: TextView = view.tvDescription
    private var tvHealthPoint: TextView = view.tvHealthPoint
    private var tvCost: TextView = view.tvCost
    private var tvMinLvl: TextView = view.tvMinLvl

    fun initViewHolder(
        health: Health,
        listener: HealthAdapterListener
    ) {
        this.listener = listener
        tvTitle.text = health.title
        tvDescription.text = health.description
        tvHealthPoint.text = "+${health.healthPoint}hp"
        tvCost.text = "-${health.cost}$"
        imgIcon.loadDrawable(R.drawable.ic_bodybuilder)

        when {
            playerSession.player.lvl < health.minLvl -> errorMinLvlAction(health)
            playerSession.player.money < health.cost -> errorNotEnoughMoneyAction()
            else -> performSuccessHealthAction(health)
        }
    }

    private fun errorMinLvlAction(health: Health) {
        view.setOnClickListener { }
        view.background(R.color.gray_light)
        tvMinLvl.text = "${health.minLvl} lvl"
        tvMinLvl.visibility = View.VISIBLE
        view.setOnClickListener {} // clear click listener
    }

    private fun errorNotEnoughMoneyAction() =
        view.setOnClickListener { view.toast(R.string.health_action_not_enough_money) }

    private fun performSuccessHealthAction(health: Health) =
        view.setOnClickListener { listener.onClick(health.createUpdateHealth()) }
}

interface HealthAdapterListener {
    fun onClick(updateHealth: UpdateHealth)
}
