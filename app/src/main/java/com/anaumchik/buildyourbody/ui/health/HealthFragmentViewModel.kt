package com.anaumchik.buildyourbody.ui.health

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anaumchik.buildyourbody.data.entity.Health

class HealthFragmentViewModel(healthRepository: HealthRepository) : ViewModel() {
    val updateAdapter = MutableLiveData<List<Health>>()

    init {
        val items = healthRepository.getItems()
        updateAdapter.postValue(items)
    }

    fun onAdapterItemClick(health: Health) {
        // TODO do item click logic
    }
}
