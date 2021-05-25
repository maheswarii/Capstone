package com.yps.layani.admin.datalocal

import com.yps.layani.admin.model.Complaint

object KomplainData {
    private val nama = arrayOf(
        "maheswarii",
        "beliaa",
        "faris",
        "ivanliu",
        "fadhlan",
        "hashfii",
        "bubu"
    )

    private val komplain = arrayOf(
        "Mati lampu terus padahal udah bayar!",
        "jalan rusak bgt heran",
        "mati lampu!!",
        "jatoh grgr jalan rusak",
        "jalan rusak nyusahin",
        "gelapp woy mati lampu",
        "idih jelek bgt jalan rusak"
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