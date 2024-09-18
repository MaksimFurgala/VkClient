package com.example.vkclient.data.mapper

import com.example.vkclient.data.model.profile.ProfileDataModel
import com.example.vkclient.domain.entity.Profile
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapResponseToProfile(profile: ProfileDataModel): Profile {
        return Profile(
            id = profile.id,
            firstName = profile.firstName,
            lastName = profile.lastName,
            avatarUrl = profile.avatarUrl,
            birthday = formatBirthdayDate(profile.birthday)
        )
    }

    private fun formatBirthdayDate(date: String): String {
        val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(date)
        return inputFormat?.let { SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(it) }
            ?: "нет данных"
    }
}