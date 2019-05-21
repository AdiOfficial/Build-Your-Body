package com.anaumchik.buildyourbody.ui.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.UpdateHealth
import com.anaumchik.buildyourbody.data.utils.alertDialog
import com.anaumchik.buildyourbody.data.utils.string
import com.anaumchik.buildyourbody.data.utils.toolbarTitle
import com.anaumchik.buildyourbody.ui.health.adapter.HealthAdapter
import com.anaumchik.buildyourbody.ui.health.adapter.HealthAdapterListener
import kotlinx.android.synthetic.main.fragment_health.*
import org.koin.android.viewmodel.ext.android.viewModel

class HealthFragment : Fragment() {
    private val viewModel: HealthFragmentViewModel by viewModel()
    private val adapter by lazy { HealthAdapter() }

    private val adapterListener = object : HealthAdapterListener {
        override fun onClick(update: UpdateHealth) {
            viewModel.onAdapterItemClick(update)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_health, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(R.string.bottom_menu_health)
        initAdapter()
        observeViewModel()
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        rvHealth.adapter = adapter
        rvHealth.layoutManager = layoutManager
        rvHealth.setHasFixedSize(true)
        rvHealth.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
        adapter.listener = adapterListener
    }

    private fun observeViewModel() {
        viewModel.updateAdapter.observe(this, Observer { adapter.data = it })
        viewModel.finish.observe(this, Observer { requireActivity().onBackPressed() })
        viewModel.showLevelUpDialog.observe(this, Observer { showDialogLevelUp(it) })
    }

    private fun showDialogLevelUp(playerLvl: Int) {
        alertDialog(
            layoutRes = R.layout.dialog_level_up,
            titleIdRes = R.id.tvTitle,
            titleStringRes = R.string.dialog_level_up_title,
            descriptionIdRes = R.id.tvDescription,
            description = string(R.string.dialog_level_up_description, playerLvl),
            btnIdRes = R.id.btnOk
        ) { viewModel.onFinishScreen() }
    }
}

