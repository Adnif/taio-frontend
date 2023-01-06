package com.taio.taio.data

import com.taio.taio.R
import com.taio.taio.domain.model.SiganatureList
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.domain.model.UserRequested
import java.security.Signature

class DataSource() {
    fun loadUser(): List<User>{
        return listOf(
            User(
                R.drawable.avatar,
                1,
                "Ahmad Bani Faqih",
                "adnonef",
                "Bani@gmail.com",
                "2342325",
                "Bani123",
                listOf(
                    User(R.drawable.avatar, name = "Ahmad Bani Faqih"),
                    User(R.drawable.avatar, name = "Riszky Hermawan")
                ),
                listOf(
                    User(R.drawable.avatar, name = "Ahmad Luhur Pakerti"),
                    User(R.drawable.avatar, name = "Ridwan Effendy")
                ),
                listOf(
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        requested = true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        requested = true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        requested = true
                    ),
                )
            ),
            User(
                R.drawable.avatar,
                2,
                "Riszky Hermawan",
                "rizkyjr",
                "Rizky@gmail.com",
                "2342325",
                "Rizky123",
                listOf(
                    User(R.drawable.avatar, name = "Ahmad Bani Faqih"),
                    User(R.drawable.avatar, name = "Riszky Hermawan")
                ),
                listOf(
                    User(R.drawable.avatar, name = "Ahmad Luhur Pakerti"),
                    User(R.drawable.avatar, name = "Ridwan Effendy")
                ),
                listOf(
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        requested = true
                    ),
                )
            ),
            User(
                R.drawable.avatar,
                3,
                "Ridwan Effendy",
                "Ridwanrr",
                "Ridwan@gmail.com",
                "2342325",
                "Ridwan123",
                listOf(
                    User(R.drawable.avatar, name = "Ahmad Bani Faqih"),
                    User(R.drawable.avatar, name = "Riszky Hermawan")
                ),
                listOf(
                    User(R.drawable.avatar, name = "Ahmad Luhur Pakerti"),
                    User(R.drawable.avatar, name = "Ridwan Effendy")
                ),
                listOf(
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        requested = true
                    ),
                    SiganatureList(
                        "Test Aja",
                        "31/12/2022",
                        "12345678",
                        "Test aja udah ini buat deskripsi dokumen tanda tangan",
                        requested = true
                    ),
                )
            ),
        )
    }
    fun loadFastRequest(): List<User> {
        return listOf<User>(
            User(R.drawable.avatar, name = "Ahmad Bani Faqih"),
            User(R.drawable.avatar, name = "Riszky Hermawan")
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

    fun loadListSignature(): List<SiganatureList> {
        return listOf<SiganatureList>(
            SiganatureList(
                "Test Aja",
                "31/12/2022",
                "12345678",
                "Test aja udah ini buat deskripsi dokumen tanda tangan"
            ),
            SiganatureList(
                "Test Aja",
                "31/12/2022",
                "12345678",
                "Test aja udah ini buat deskripsi dokumen tanda tangan"
            ),
            SiganatureList(
                "Test Aja",
                "31/12/2022",
                "12345678",
                "Test aja udah ini buat deskripsi dokumen tanda tangan"
            ),
            SiganatureList(
                "Test Aja",
                "31/12/2022",
                "12345678",
                "Test aja udah ini buat deskripsi dokumen tanda tangan"
            )
        )
    }
}