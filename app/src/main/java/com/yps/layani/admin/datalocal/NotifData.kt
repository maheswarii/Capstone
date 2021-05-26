package com.yps.layani.admin.datalocal

import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.Notifications

object NotifData {
    private val pesan = arrayOf(
        "maheswarii"
    )

    val listDataNotif: ArrayList<Notifications>
        get() {
            val list = arrayListOf<Notifications>()
            for (position in pesan.indices) {
                val data = Notifications()
                data.pesan = pesan[position]
                list.add(data)
            }
            return list
        }
}