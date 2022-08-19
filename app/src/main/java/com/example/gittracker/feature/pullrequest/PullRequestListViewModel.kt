package com.example.gittracker.feature.pullrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestListViewModel
@Inject
constructor(
    private val repository: PullRequestListRepository,
) : ViewModel() {

    private val _closedPullRequestList = MutableStateFlow<List<PullRequestData>?>(null)
    val closedPullRequestList = _closedPullRequestList.asStateFlow()

    init {
        viewModelScope.launch {
            _closedPullRequestList.value = getClosedPullRequests()
        }
    }

    private suspend fun getClosedPullRequests(): List<PullRequestData>? {
        return repository.getClosedPullRequests(
            "Kotlin", "kotlinx.coroutines"
        )
    }
}
