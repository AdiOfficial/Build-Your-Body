package com.anaumchik.buildyourbody.ui.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.entity.UpdateWork
import com.anaumchik.buildyourbody.data.utils.toolbarTitle
import com.anaumchik.buildyourbody.ui.work.adapter.WorkAdapter
import com.anaumchik.buildyourbody.ui.work.adapter.WorkAdapterListener
import kotlinx.android.synthetic.main.fragment_work.*
import org.koin.android.viewmodel.ext.android.viewModel

class WorkFragment : Fragment() {
    private val viewModel: WorkFragmentViewModel by viewModel()
    private val adapter by lazy { WorkAdapter() }

    private val adapterListener = object : WorkAdapterListener {
        override fun onClick(update: UpdateWork) {
            viewModel.onAdapterItemClick(update)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_work, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(R.string.bottom_menu_work)
        initAdapter()
        observeViewModel()
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        rvWork.adapter = adapter
        rvWork.layoutManager = layoutManager
        rvWork.setHasFixedSize(true)
        rvWork.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
        adapter.listener = adapterListener
    }

    private fun observeViewModel() {
        viewModel.updateAdapter.observe(this, Observer { adapter.data = it })
        viewModel.finish.observe(this, Observer { requireActivity().onBackPressed() })
    }
}
