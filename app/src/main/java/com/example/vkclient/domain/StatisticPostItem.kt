package com.example.vkclient.domain

data class StatisticPostItem(
    val type: StatisticType,
    val count: Int = 0
)

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}
