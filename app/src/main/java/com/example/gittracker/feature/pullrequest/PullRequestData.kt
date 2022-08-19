package com.example.gittracker.feature.pullrequest

import java.util.*

data class PullRequestData(
    val title: String,
    val createdAt: Date,
    val closedAt: Date,
    val username: String,
    val userImageUrl: String?,
)
