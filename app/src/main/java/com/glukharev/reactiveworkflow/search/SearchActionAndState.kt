package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Action

sealed class SearchInteractorInputAction : Action {
    object StartLoadingAction : SearchInteractorInputAction()
    data class PageLoaded(val dto: SearchDTO) : SearchInteractorInputAction()
}

sealed class SearchInteractorAction : Action {
    object Error : SearchInteractorAction()
    object StartLoadFromNetwork : SearchInteractorAction()
    data class SearchResult(val searchResult: String) : SearchInteractorAction()
}

sealed class SearchUiState {
    object Loading : SearchUiState()
    object Error : SearchUiState()
    data class ShowContent(val text: String) : SearchUiState()
}