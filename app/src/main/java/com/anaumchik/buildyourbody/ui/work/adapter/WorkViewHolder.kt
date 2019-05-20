package com.anaumchik.buildyourbody.ui.work.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.UpdateWork
import com.anaumchik.buildyourbody.data.entity.Work
import com.anaumchik.buildyourbody.data.entity.createUpdateWork
import com.anaumchik.buildyourbody.data.utils.SessionManager
import com.anaumchik.buildyourbody.data.utils.background
import com.anaumchik.buildyourbody.data.utils.loadDrawable
import com.anaumchik.buildyourbody.data.utils.toast
import kotlinx.android.synthetic.main.item_work.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WorkViewHolder(private val view: View) : RecyclerView.ViewHolder(view), KoinComponent {
    private val playerSession = inject<SessionManager>().value

    private lateinit var listener: WorkAdapterListener
    private lateinit var work: Work
    private var imgIcon: ImageView = view.imgIcon
    private var tvTitle: TextView = view.tvTitle
    private var tvDescription: TextView = view.tvDescription
    private var tvAdjustMoney: TextView = view.tvAdjustMoney
    private var tvCostHealth: TextView = view.tvCostHealth
    private var tvMinLvl: TextView = view.tvMinLvl

    fun initViewHolder(
        work: Work,
        listener: WorkAdapterListener
    ) {
        this.work = work
        this.listener = listener
        tvTitle.text = work.title
        tvDescription.text = work.description
        tvAdjustMoney.text = "+${work.adjustMoney}$"
        tvCostHealth.text = "-${work.costHealth}hp"
        imgIcon.loadDrawable(R.drawable.ic_bodybuilder)

        when {
            isNotEnoughLvl() -> errorNotEnoughLvlAction()
            isNotEnoughHealth() -> errorNotEnoughHealthAction()
            isNotEnoughTime() -> errorNotEnoughTimeAction()
            else -> performSuccessHealthAction()
        }
    }

    private fun isNotEnoughHealth(): Boolean = playerSession.player.health < work.costHealth

    private fun isNotEnoughLvl(): Boolean = playerSession.player.lvl < work.minLvl

    private fun isNotEnoughTime(): Boolean = playerSession.player.time == 0

    private fun errorNotEnoughLvlAction() {
        view.setOnClickListener { }
        view.background(R.color.gray_light)
        tvMinLvl.text = "${work.minLvl} lvl"
        tvMinLvl.visibility = View.VISIBLE
        view.setOnClickListener {} // clear click listener
    }

    private fun errorNotEnoughHealthAction() =
        view.setOnClickListener { view.toast(R.string.health_action_not_enough_health) }

    private fun errorNotEnoughTimeAction() =
        view.setOnClickListener { view.toast(R.string.health_action_not_enough_time) }

    private fun performSuccessHealthAction() =
        view.setOnClickListener { listener.onClick(work.createUpdateWork()) }
}

interface WorkAdapterListener {
    fun onClick(update: UpdateWork)
}
