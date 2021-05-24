package com.yps.layani.admin.datalocal

import com.yps.layani.R
import com.yps.layani.admin.model.Information

object InfoData {
    private val infoNames = arrayOf(
        "Gangguan Listrik",
        "Jalan Rusak"
    )

    private val infoImages = arrayOf(
        "https://asset.kompas.com/crops/Sq4Qb9TMU8-m1ogcY58mbItBnBM=/0x0:864x576/750x500/data/photo/2019/08/07/5d4a8adaf2ad4.jpg",
        "https://media.suara.com/pictures/480x260/2019/02/27/50021-foto-kreatif-mengkritik-jalan-rusak-di-batu-marta-oku.jpg"

    )

    private val infoDetail = arrayOf(
        "Kejadian yang tidak diinginkan dan mengganggu kerja alat listrik. Akibat gangguan, peralatan listrik tidak berfungsi dan sangat merugikan. Bahkan gangguan yang luas dapat mengganggu keseluruhan kerja sistem produksi dan akan merugikan perusahaan sekaligus pelanggan.",
        "Banyaknya jalan yang rusak di Tanah Air saat musim penghujan tiba menjadi pekerjaan rumah yang tidak ada hentinya bagi pemerintah. Kondisi jalan yang rusak sering menyebabkan kecelakaan, bahkan mengakibatkan jatuh korban dan kerugian harta benda akibat terperosok atau terserempet atau ditabrak kendaraan lain saat menghindari jalan rusak tersebut."

    )

    val listData: ArrayList<Information>
    get(){
        val list = arrayListOf<Information>()
        for (position in infoNames.indices){
            val info = Information()
            info.title = infoNames[position]
            info.photo = infoImages[position]
            info.content_detail_info = infoDetail[position]
            list.add(info)
        }
        return list
    }
}