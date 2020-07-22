package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Interactor

class SearchInteractor : Interactor<SearchInteractorInputAction, SearchInteractorAction>() {

    override fun handleAction(inputAction: SearchInteractorInputAction?): SearchInteractorAction? {
        return when (inputAction) {
            is SearchInteractorInputAction.StartLoadingAction -> {
                SearchInteractorAction.StartLoadFromNetwork
            }
            is SearchInteractorInputAction.PageLoaded -> {
                SearchInteractorAction.SearchResult(searchResult = inputAction.dto.pageText)
            }
            else -> null
        }
    }
}