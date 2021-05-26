package com.yps.layani.admin.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yps.layani.R
import com.yps.layani.admin.datalocal.NotifData
import com.yps.layani.admin.model.Notifications
import com.yps.layani.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding

    private var listNotif: ArrayList<Notifications> = arrayListOf()
    //private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding?.rvNotifAdmin?.setHasFixedSize(true)
        listNotif.addAll(NotifData.listDataNotif)
        showRecycleNotificationsList()

        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    private fun showRecycleNotificationsList() {
        binding?.rvNotifAdmin?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listNotifAdapter = NotificationsAdapter(listNotif)
        binding?.rvNotifAdmin?.adapter = listNotifAdapter
    }
}