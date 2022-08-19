package com.example.gittracker.di

import com.example.gittracker.feature.pullrequest.PullRequestListApiService
import com.example.gittracker.feature.pullrequest.PullRequestListNetworkManager
import com.example.gittracker.feature.pullrequest.PullRequestListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providePullRequestListRepository(
        networkManager: PullRequestListNetworkManager,
    ): PullRequestListRepository = PullRequestListRepository(
        networkManager
    )

    @Singleton
    @Provides
    fun providePullRequestListNetworkManager(
        apiService: PullRequestListApiService,
    ): PullRequestListNetworkManager = PullRequestListNetworkManager(
        apiService
    )

    @Singleton
    @Provides
    fun providePullRequestApiService(
        retrofit: Retrofit,
    ): PullRequestListApiService {
        return retrofit.create(PullRequestListApiService::class.java)
    }
}
