package com.anaumchik.buildyourbody.ui.health.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anaumchik.buildyourbody.data.entity.Health
import com.anaumchik.buildyourbody.data.utils.SessionManager
import com.anaumchik.buildyourbody.data.utils.background
import com.anaumchik.buildyourbody.data.utils.loadDrawable
import kotlinx.android.synthetic.main.item_health.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class HealthViewHolder(private val view: View) : RecyclerView.ViewHolder(view), KoinComponent {
    private val playerSession = inject<SessionManager>().value

    var imgIcon: ImageView = view.imgIcon
    var tvTitle: TextView = view.tvTitle
    var tvDescription: TextView = view.tvDescription
    var tvHealthPoint: TextView = view.tvHealthPoint
    var tvCost: TextView = view.tvCost
    var tvMinLvl: TextView = view.tvMinLvl

    fun initViewHolder(
        health: Health,
        listener: HealthAdapterListener
    ) {
        tvTitle.text = health.title
        tvDescription.text = health.description
        tvHealthPoint.text = "+${health.healthPoint}hp"
        tvCost.text = "-${health.cost}$"

        imgIcon.loadDrawable(com.anaumchik.buildyourbody.R.drawable.ic_bodybuilder)

        if (playerSession.player.lvl < health.minLvl) {
            view.setOnClickListener { }
            view.background(com.anaumchik.buildyourbody.R.color.gray_light)
            tvMinLvl.text = "${health.minLvl} lvl"
            tvMinLvl.visibility = View.VISIBLE
            view.setOnClickListener {} // clear click listener
        } else {
            view.setOnClickListener {
                listener.onClick(health)
            }
        }
    }
}

interface HealthAdapterListener {
    fun onClick(health: Health)
}
