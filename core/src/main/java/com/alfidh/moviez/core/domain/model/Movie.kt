package com.alfidh.moviez.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id: Int,
    val title: String,
    val date: String,
    val image: String,
    val rate: Double,
    val desc: String,
    val favorite: Boolean
) : Parcelable