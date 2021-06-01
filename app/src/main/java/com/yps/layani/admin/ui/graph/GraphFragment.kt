package com.yps.layani.admin.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yps.layani.admin.adapter.StatsAdapter
import com.yps.layani.admin.model.Stats
import com.yps.layani.admin.ui.home.ViewModelFactory
import com.yps.layani.databinding.FragmentGraphBinding

class GraphFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_PARCEL = "section_parcel"
    }

    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvStats.layoutManager = LinearLayoutManager(this.context)
        val user = arguments?.getParcelable<Stats>(ARG_SECTION_PARCEL)
        val graphViewModel: GraphViewModel = ViewModelProvider(
            this@GraphFragment, ViewModelFactory(requireActivity().application)).get(GraphViewModel::class.java)
        val adapter = StatsAdapter()
        binding.rvStats.adapter = adapter

        showLoading(true)
        graphViewModel.getLeaderboard(arguments?.getString("token") ?: "")

        graphViewModel.stats.observe(viewLifecycleOwner, { loadStats ->
            if (loadStats != null) {
                adapter.setData(loadStats)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}