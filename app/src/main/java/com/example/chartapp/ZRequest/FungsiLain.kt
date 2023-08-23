package com.example.chartapp.ZRequest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.chartapp.Object.ObjectTransaksi
import com.example.chartapp.PortofolioLayout
import java.text.DecimalFormat

object FungsiLain {

    fun setView(idname: String, cntx: Context) {
        val bund = Bundle()
        lateinit var listdata: List<ObjectTransaksi>
        listdata = ArrayList<ObjectTransaksi>()
        var label = ""
        if (idname.equals("Tarik Tunai (55%)", ignoreCase = true) || idname == "Tarik Tunai (55%)") {
            listdata = listdata + ObjectTransaksi("21/01/2023", "1000000")
            listdata = listdata + ObjectTransaksi("20/01/2023", "500000")
            listdata = listdata + ObjectTransaksi("19/01/2023", "1000000")
            label = "Riwayat Transaksi Tarik Tunai"
        } else if (idname.equals("QRIS Payment (31%)", ignoreCase = true) || idname == "QRIS Payment (31%)") {
            listdata = listdata + ObjectTransaksi("21/01/2023", "159000")
            listdata = listdata + ObjectTransaksi("20/01/2023", "35000")
            listdata = listdata + ObjectTransaksi("19/01/2023", "1500")
            label = "Riwayat Transaksi QRIS Payment"
        } else if (idname.equals("Topup Gopay (7.7%)", ignoreCase = true) || idname == "Topup Gopay (7.7%)") {
            listdata = listdata + ObjectTransaksi("21/01/2023", "200000")
            listdata = listdata + ObjectTransaksi("20/01/2023", "195000")
            listdata = listdata + ObjectTransaksi("19/01/2023", "5000000")
            label = "Riwayat Transaksi Topup Gopay"
        } else if (idname.equals("Lainnya (6.3%)", ignoreCase = true) || idname == "Lainnya (6.3%)") {
            listdata = listdata + ObjectTransaksi("21/01/2023", "1000000")
            listdata = listdata + ObjectTransaksi("20/01/2023", "500000")
            listdata = listdata + ObjectTransaksi("19/01/2023", "1000000")
            label = "Riwayat Transaksi Lainnya"
        }
        bund.putParcelableArrayList("obyek", listdata as ArrayList<ObjectTransaksi>)
        bund.putString("label", label)
        val intent = Intent(cntx, PortofolioLayout::class.java)
        intent.putExtras(bund)
        cntx.startActivity(intent)
    }

    fun setFormatText(obyek: Number?, pattern: String?): String {
        var result = ""
        val dfNom = DecimalFormat(pattern)
        val dfResult = dfNom.format(obyek)
        if (!dfResult.equals("0")) {
            result = dfResult
        }
        return result
    }

}