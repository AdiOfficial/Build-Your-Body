package com.anaumchik.buildyourbody.ui.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anaumchik.buildyourbody.R
import com.anaumchik.buildyourbody.data.utils.toolbarTitle
import org.koin.android.viewmodel.ext.android.viewModel

class WorkFragment : Fragment() {
    private val viewModel: WorkFragmentViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_work, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(R.string.bottom_menu_work)
    }
}
