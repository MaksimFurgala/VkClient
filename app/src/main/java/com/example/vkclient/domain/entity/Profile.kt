package com.example.vkclient.domain.entity

data class Profile(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String,
    val birthday: String,
)