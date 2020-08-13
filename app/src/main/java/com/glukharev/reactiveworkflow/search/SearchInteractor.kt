package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Action
import com.glukharev.framework.Interactor

class SearchInteractor : Interactor() {

    override fun handleAction(inputAction: Action?): Action? {
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