package com.example.gittracker.feature.pullrequest

import javax.inject.Inject

class PullRequestListNetworkManager
@Inject
constructor(
    private val apiService: PullRequestListApiService,
) {
    suspend fun getClosedPullRequests(
        username: String,
        repoName: String,
    ): List<PullRequestData>? = try {
        apiService.getClosedPullRequests(
            username,
            repoName,
        ).map {
            PullRequestData(
                title = it.title,
                username = it.user.login,
                userImageUrl = it.user.avatar_url,
                createdAt = it.created_at,
                closedAt = it.closed_at,
            )
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        null
    }
}
