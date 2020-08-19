package com.glukharev.reactiveworkflow.search

import androidx.lifecycle.viewModelScope
import com.glukharev.framework.ScreenActionPool
import com.glukharev.reactiveworkflow.Component

class SearchComponent(
    private val interactor: SearchInteractor,
    private val reducer: SearchReducer,
    private val view: SearchView,
    private val actionPool: ScreenActionPool
) : Component() {

    override fun bindToView() {
        view.bind()
    }

    override fun wire() {
        // TODO use different scopes
        view.bindToScope(viewModelScope)
        interactor.bindToScope(viewModelScope)
        reducer.bindToScope(viewModelScope)
        actionPool.bindToScope(viewModelScope)

        view.subscribe { uiAction ->
            interactor.handle(uiAction)
            actionPool.pushSharedAction(uiAction)
            reducer.handle(uiAction)
        }

        interactor.subscribe { interactorOutputAction ->
            actionPool.pushSharedAction(interactorOutputAction)
            reducer.handle(interactorOutputAction)
        }

        actionPool.subscribeSharedAction { sharedAction ->
            // don`t listen to actions sent by this component to ActionPool
            if (sharedAction !is SearchInteractorInputAction || sharedAction !is SearchInteractorAction) {
                interactor.handle(sharedAction)
                reducer.handle(sharedAction)
            }
        }

        reducer.subscribe { state ->
            view.render(state)
        }
    }

    override fun unWire() {

        // todo instead putting containerView as null - use lifecycleScope to observe flow from component
//        view.bind(null)

        view.bindToScope(null)
        interactor.bindToScope(null)
        reducer.bindToScope(null)
    }
}