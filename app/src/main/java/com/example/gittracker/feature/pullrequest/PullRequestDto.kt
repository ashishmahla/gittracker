package com.example.gittracker.feature.pullrequest

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PullRequestDto(
    val title: String,
    val user: UserDto,
    val created_at: Date,
    val closed_at: Date,
)

@JsonClass(generateAdapter = true)
data class UserDto(
    val login: String,
    val avatar_url: String?,
)
