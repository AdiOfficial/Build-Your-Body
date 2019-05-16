package com.anaumchik.buildyourbody.ui.health.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.Health
import com.anaumchik.buildyourbody.data.utils.inflate

class HealthAdapter : RecyclerView.Adapter<HealthViewHolder>() {
    var data = listOf<Health>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var listener: HealthAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder =
        HealthViewHolder(parent.inflate(R.layout.item_health))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val health = data[position]
        holder.initViewHolder(health, listener)
    }
}
