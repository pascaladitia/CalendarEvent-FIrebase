package com.pascal.calendereventapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kehadiran (
    var start: String? = null,
    var end: String? = null,
    var kehadiran: String? = null,
    var color: String? = null,
    var id: String? = null
) : Parcelable
{
    constructor(): this("", "", "", "", "")
}