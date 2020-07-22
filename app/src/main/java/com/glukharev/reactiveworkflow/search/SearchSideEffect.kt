package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Interactor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SearchSideEffect : Interactor<SearchInteractorAction, SearchInteractorInputAction>() {

    override fun handleAction(inputAction: SearchInteractorAction?): SearchInteractorInputAction? {
        return when (inputAction) {
            is SearchInteractorAction.StartLoadFromNetwork -> {
                // Emulate network
                SearchInteractorInputAction.PageLoaded(dto = SearchDTO(pageText = "this text has been loaded from network"))
            }
            else -> null
        }
    }
}