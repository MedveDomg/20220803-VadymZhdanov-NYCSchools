package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medvedomg.a20220803_vadymzhdanov_nycschools.databinding.FragmentSchoolListBinding
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.dialog.SatScoreDialogDialog
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.util.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SchoolListFragment : Fragment() {

    private lateinit var binding: FragmentSchoolListBinding

    private val viewModel by viewModel<SchoolListViewModel>()

    private val adapter by lazy {
        SchoolListAdapter {
            val dialog = SatScoreDialogDialog.newInstance(it)
            val tag = dialog::class.simpleName
            dialog.show(parentFragmentManager, tag)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = this@SchoolListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, ::setState)
    }

    private fun setState(state: ViewState<List<SchoolModel>>) {
        with(binding) {
            when (state) {
                is ViewState.Success -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.GONE
                    adapter.setData(state.data)
                    recyclerView.visibility = View.VISIBLE
                }
                is ViewState.Error -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    tvError.text = state.errorMessage
                }
                is ViewState.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    adapter.setData(emptyList())
                }
            }
        }
    }
}