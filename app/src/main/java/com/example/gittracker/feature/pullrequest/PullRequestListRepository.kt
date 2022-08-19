package com.example.gittracker.feature.pullrequest

import javax.inject.Inject

class PullRequestListRepository
@Inject
constructor(
    private val networkManager: PullRequestListNetworkManager,
) {
    suspend fun getClosedPullRequests(
        username: String,
        repoName: String,
    ): List<PullRequestData>? {
        return networkManager.getClosedPullRequests(username, repoName)
    }
}
