package com.yps.layani.admin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yps.layani.admin.ui.detail.DetailInfoActivity
import com.yps.layani.admin.adapter.ComplaintAdapter
import com.yps.layani.admin.adapter.ListInfoAdapter
import com.yps.layani.admin.datalocal.InfoData
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.Information
import com.yps.layani.admin.preferences.UserPreference
import com.yps.layani.admin.ui.detail.DetailComplaintActivity
import com.yps.layani.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var listInfo: ArrayList<Information> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.show()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvComplaint.layoutManager = LinearLayoutManager(this.context)
        val homeViewModel: HomeViewModel = ViewModelProvider(
            this@HomeFragment,
            ViewModelFactory(requireActivity().application)
        ).get(HomeViewModel::class.java)
        val adapter = ComplaintAdapter()
        binding.rvComplaint.adapter = adapter

        val pref = UserPreference(requireContext())
        homeViewModel.getUserComplaint(pref.token)

        showLoading(true)
        homeViewModel.users.observe(viewLifecycleOwner, { loadComplaint ->
            if (loadComplaint != null) {
                adapter.setData(loadComplaint)
                showLoading(false)

                binding.lottieEmpty.visibility =
                    if (loadComplaint.isNotEmpty()) View.GONE else View.VISIBLE
            }
        })

        adapter.setOnItemClickCallback(object : ComplaintAdapter.OnItemClickCallback {

            override fun onItemClicked(data: Complaint) {
                val intent = Intent(context, DetailComplaintActivity::class.java)
                //intent.putExtra("id", Complaint.)
                startActivity(intent)
            }
        })

        binding.rvInfo.setHasFixedSize(true)
        listInfo.addAll(InfoData.listData)
        showRecyclerInfoList()
    }


    private fun showRecyclerInfoList() {
        binding.rvInfo.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listInfoAdapter = ListInfoAdapter(listInfo)
        binding.rvInfo.adapter = listInfoAdapter

        listInfoAdapter.setOnItemClickCallback(object : ListInfoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Information) {
                showSelectedEvent(data)
            }
        })
    }

    private fun showSelectedEvent(information: Information) {
        val intent = Intent(context, DetailInfoActivity::class.java).apply {
            putExtra(DetailInfoActivity.EXTRA_INFO, information)
        }
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
