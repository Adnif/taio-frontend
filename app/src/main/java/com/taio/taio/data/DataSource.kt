package com.taio.taio.data

import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequest

class DataSource() {
    fun loadFastRequest(): List<User> {
        return listOf<User>(
            User(R.drawable.avatar, "Ahmad Bani Faqih"),
            User(R.drawable.avatar, "Riszky Hermawan")
        )
    }

    fun loadRequest(): List<UserRequest> {
        return listOf<UserRequest>(
            UserRequest(
                R.drawable.avatar,
                "Surat Keterangan Mahasiswa",
                "Ahmad Luhur Pakerti",
                "Ngetes Doang"),
            UserRequest(
                R.drawable.avatar,
                "Surat Keterangan Juga",
                "Ridwan Effendy",
                "Ini buat ngetes juga"
            ),
            UserRequest(
                R.drawable.avatar,
                "Surat Apa gitu yang resmi",
                "Kurosaki wololo nan elok",
                "Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang"),
        )
    }
}