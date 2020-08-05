package com.glukharev.reactiveworkflow.progress

import com.glukharev.framework.Action
import com.glukharev.framework.Reducer
import com.glukharev.reactiveworkflow.search.SearchInteractorAction

class ProgressReducer : Reducer<ProgressUiState>() {

    override fun handleAction(action: Action?): ProgressUiState? {
        return when (action) {
            is SearchInteractorAction.SearchResult -> ProgressUiState.Hide
            is SearchInteractorAction.StartLoadFromNetwork -> ProgressUiState.Show
            is SearchInteractorAction.Error -> ProgressUiState.Hide
            else -> null
        }
    }
}