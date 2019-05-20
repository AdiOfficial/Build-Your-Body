package com.anaumchik.buildyourbody.ui.work

import android.app.Application
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.Work

class WorkRepository(private val application: Application) {
    fun getItems(): List<Work> {
        val items = mutableListOf<Work>()
        val titles = application.resources.getStringArray(R.array.work_title)
        val descriptions = application.resources.getStringArray(R.array.work_description)
        val healthPoints = application.resources.getIntArray(R.array.work_adjust_money)
        val costs = application.resources.getIntArray(R.array.work_cost_health)
        val minLvls = application.resources.getIntArray(R.array.work_min_lvl)
        val experiences = application.resources.getIntArray(R.array.work_experience)

        for (i in 0 until titles.size) {
            items.add(
                Work(
                    i,
                    R.drawable.ic_launcher,
                    titles[i],
                    descriptions[i],
                    healthPoints[i],
                    costs[i],
                    minLvls[i],
                    experiences[i]
                )
            )
        }

        return items
    }
}
