package com.example.chartapp.Object

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ObjectTransaksi(
    var trxDate: String = "",
    var nominal: String = ""
): Parcelable