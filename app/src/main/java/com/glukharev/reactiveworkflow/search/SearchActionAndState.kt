package com.glukharev.reactiveworkflow.search

sealed class SearchInteractorInputAction {
    object StartLoadingAction : SearchInteractorInputAction()
    data class PageLoaded(val dto: SearchDTO) : SearchInteractorInputAction()
}

sealed class SearchInteractorAction {
    object Error : SearchInteractorAction()
    object StartLoadFromNetwork : SearchInteractorAction()
    data class SearchResult(val searchResult: String) : SearchInteractorAction()
}

sealed class SearchUiState {
    object Loading : SearchUiState()
    object Error : SearchUiState()
    data class ShowContent(val text: String) : SearchUiState()
}