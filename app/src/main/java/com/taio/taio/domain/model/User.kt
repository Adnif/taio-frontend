package com.taio.taio.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @DrawableRes val avatar: Int,
    val id: Int = 0,
    val name: String,
    val userName: String = "example",
    val email: String = "example@gmail.com",
    val phone: String = "087770775311",
    val password: String = "Testing123",
    val follower: List<User> = emptyList(),
    val following: List<User> = emptyList(),
    val tergenerate: List<SiganatureList> = emptyList(),
    val self: List<SiganatureList> = tergenerate.filter{ it.self },
    val requested: List<SiganatureList> = tergenerate.filter{ it.requested }
): Parcelable
