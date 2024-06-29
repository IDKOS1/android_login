package com.example.loginapp.Class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val password: String,
    val name: String,
    val age: Int,
    val gender: Boolean,
    val nickname: String
): Parcelable


object UserData {
    var userList: MutableMap<String, User> = mutableMapOf()
}
