package com.anaumchik.buildyourbody.ui.health

import android.app.Application
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.Health

class HealthRepository(private val application: Application) {
    fun getItems(): List<Health> {
        val items = mutableListOf<Health>()
        val titles = application.resources.getStringArray(R.array.health_title)
        val descriptions = application.resources.getStringArray(R.array.health_description)
        val healthPoints = application.resources.getIntArray(R.array.health_adjust_health)
        val costs = application.resources.getIntArray(R.array.health_cost_money)
        val minLvls = application.resources.getIntArray(R.array.health_min_lvl)
        val experiences = application.resources.getIntArray(R.array.health_experience)

        for (i in 0 until titles.size) {
            items.add(
                Health(
                    i,
                    R.drawable.ic_bodybuilder,
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
