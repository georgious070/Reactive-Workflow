package com.glukharev.reactiveworkflow.search

sealed class SearchUiAction {
    object StartLoadingAction : SearchUiAction()
    data class TimeSlotChanged(val string: String) : SearchUiAction()
}

sealed class SearchInteractorAction {
    object Loading : SearchInteractorAction()
    object Error : SearchInteractorAction()
    data class PageLoaded(val dto: SearchDTO) : SearchInteractorAction()
}

sealed class SearchUiState {
    object Loading : SearchUiState()
    object Error : SearchUiState()
    data class ShowContent(val text: String) : SearchUiState()
}