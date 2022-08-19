package com.example.gittracker.feature.pullrequest

import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestListApiService {
    @GET("repos/{username}/{repo_name}/pulls?state=closed")
    suspend fun getClosedPullRequests(
        @Path("username") username: String,
        @Path("repo_name") repoName: String,
    ): List<PullRequestDto>
}
