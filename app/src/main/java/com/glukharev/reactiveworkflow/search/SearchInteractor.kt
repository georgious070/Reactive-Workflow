package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Action
import com.glukharev.framework.Interactor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchInteractor : Interactor() {

    override fun handleAction(inputAction: Action?): Flow<Action?> {
        return flow {
            val s = when (inputAction) {
                is SearchInteractorInputAction.StartLoadingAction -> {
                    SearchInteractorAction.StartLoadFromNetwork
                }
                is SearchInteractorInputAction.PageLoaded -> {
                    SearchInteractorAction.SearchResult(searchResult = inputAction.dto.pageText)
                }
                else -> null
            }
            emit(s)
        }
    }
}