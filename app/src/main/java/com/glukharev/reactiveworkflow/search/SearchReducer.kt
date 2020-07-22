package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Reducer

class SearchReducer : Reducer<SearchInteractorAction?, SearchUiState>() {

    override fun handleAction(action: SearchInteractorAction?): SearchUiState? {
        return when (action) {
            is SearchInteractorAction.PageLoaded -> {
                SearchUiState.ShowContent(text = action.dto.pageText)
            }
            is SearchInteractorAction.Loading -> SearchUiState.Loading
            is SearchInteractorAction.Error -> SearchUiState.Error
            else -> null
        }
    }
}