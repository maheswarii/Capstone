package com.yps.layani.admin.ui.leaderboard

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
import com.yps.layani.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_PARCEL = "section_parcel"
    }

    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvStats.layoutManager = LinearLayoutManager(this.context)
        val user = arguments?.getParcelable<Stats>(ARG_SECTION_PARCEL)
        val leaderboardViewModel: LeaderboardViewModel = ViewModelProvider(
            this@LeaderboardFragment, ViewModelFactory(requireActivity().application)).get(LeaderboardViewModel::class.java)
        val adapter = StatsAdapter()
        binding.rvStats.adapter = adapter

        showLoading(true)
        leaderboardViewModel.getLeaderboard(arguments?.getString("token") ?: "")

        leaderboardViewModel.stats.observe(viewLifecycleOwner, { loadStats ->
            if (loadStats != null) {
                adapter.setData(loadStats)
                showLoading(false)

                binding.lottieEmpty.visibility =
                    if (loadStats.isNotEmpty()) View.GONE else View.VISIBLE
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