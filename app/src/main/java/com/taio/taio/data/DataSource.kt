package com.taio.taio.data

import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.domain.model.UserRequested

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
                "Ini deskripsi yang panjang banget ceritaya buat ngetes doang"),
        )
    }

    //Status 0 untuk Terkirim, 1 untuk ditolak, 2 untuk diterima
    fun loadRequested(): List<UserRequested> {
        return listOf<UserRequested>(
            UserRequested(
                R.drawable.avatar,
                "Surat Keterangan Mahasiswa",
                "Ahmad Luhur Pakerti",
                "Ngetes Doang",
                0
            ),
            UserRequested(
                R.drawable.avatar,
                "Surat Keterangan Juga",
                "Ridwan Effendy",
                "Ini buat ngetes juga",
                1
            ),
            UserRequested(
                R.drawable.avatar,
                "Surat Apa gitu yang resmi",
                "Kurosaki wololo nan elok",
                "Ini deskripsi yang panjang banget ceritaya buat ngetes doang",
                2
            ),
        )
    }
}