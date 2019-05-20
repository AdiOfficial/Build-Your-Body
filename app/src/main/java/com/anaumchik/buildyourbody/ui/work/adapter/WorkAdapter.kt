package com.anaumchik.buildyourbody.ui.work.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.Work
import com.anaumchik.buildyourbody.data.utils.inflate

class WorkAdapter : RecyclerView.Adapter<WorkViewHolder>() {
    var data = listOf<Work>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var listener: WorkAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder =
        WorkViewHolder(parent.inflate(R.layout.item_work))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val work = data[position]
        holder.initViewHolder(work, listener)
    }
}
