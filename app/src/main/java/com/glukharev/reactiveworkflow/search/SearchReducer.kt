package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Reducer

class SearchReducer : Reducer<SearchInteractorAction?, SearchUiState>() {

    override fun handleAction(action: SearchInteractorAction?): SearchUiState? {
        return when (action) {
            is SearchInteractorAction.SearchResult -> {
                SearchUiState.ShowContent(text = action.searchResult)
            }
            is SearchInteractorAction.StartLoadFromNetwork -> SearchUiState.Loading
            is SearchInteractorAction.Error -> SearchUiState.Error
            else -> null
        }
    }
}