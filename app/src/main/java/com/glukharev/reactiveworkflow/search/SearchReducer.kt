package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Action
import com.glukharev.framework.Reducer

class SearchReducer : Reducer<SearchUiState>() {

    override fun handleAction(action: Action?): SearchUiState? {
        return when (action) {
            is SearchInteractorAction.SearchResult -> {
                SearchUiState.ShowContent(text = action.searchResult)
            }
            is SearchInteractorAction.StartLoadFromNetwork -> SearchUiState.Loading
            is SearchInteractorAction.Error -> SearchUiState.Error
            else -> null // todo return null to prevent invoke of subscriber
        }
    }
}