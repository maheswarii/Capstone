package com.yps.layani.admin.datalocal

import com.yps.layani.admin.model.Complaint

object KomplainData {
    private val nama = arrayOf(
        "Maheswari Paramita"
    )

    private val komplain = arrayOf(
        "Lampu mati terus padahal udah bayar!"
    )

    val listDataKomplain: ArrayList<Complaint>
        get() {
            val list = arrayListOf<Complaint>()
            for (position in nama.indices) {
                val data = Complaint()
                data.name = nama[position]
                data.complaint = komplain[position]
                list.add(data)
            }
            return list
        }
}