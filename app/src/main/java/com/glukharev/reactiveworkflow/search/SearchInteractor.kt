package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Interactor

class SearchInteractor : Interactor<SearchUiAction, SearchInteractorAction>() {

    override fun handleAction(inputAction: SearchUiAction?): SearchInteractorAction? {
        return when (inputAction) {
            is SearchUiAction.StartLoadingAction -> {
                SearchInteractorAction.PageLoaded(dto = SearchDTO(pageText = "loaded from network text"))
            }
            else -> null
        }
    }
}