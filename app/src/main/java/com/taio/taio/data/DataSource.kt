package com.taio.taio.data

import com.taio.taio.R
import com.taio.taio.model.FastRequest
import com.taio.taio.model.Request

class DataSource() {
    fun loadFastRequest(): List<FastRequest> {
        return listOf<FastRequest>(
            FastRequest(R.drawable.avatar, "Ahmad Bani Faqih"),
            FastRequest(R.drawable.avatar, "Riszky Hermawan")
        )
    }

    fun loadRequest(): List<Request> {
        return listOf<Request>(
            Request(
                R.drawable.avatar,
                "Surat Keterangan Mahasiswa",
                "Ahmad Luhur Pakerti",
                "Ngetes Doang"),
            Request(
                R.drawable.avatar,
                "Surat Keterangan Juga",
                "Ridwan Effendy",
                "Ini buat ngetes juga"
            ),
            Request(
                R.drawable.avatar,
                "Surat Apa gitu yang resmi",
                "Kurosaki wololo nan elok",
                "Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang Ini deskripsi yang panjang banget ceritaya buat ngetes doang"),
        )
    }
}