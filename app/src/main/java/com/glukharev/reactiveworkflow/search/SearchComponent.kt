package com.glukharev.reactiveworkflow.search

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class Component : ViewModel() {
    abstract fun bindToView(containerView: ViewGroup?)
    abstract fun wire()
    abstract fun unWire()

    override fun onCleared() {
        super.onCleared()
        unWire()
    }
}

class SearchComponent(
    private val interactor: SearchInteractor,
    protected val sideEffect: SearchSideEffect,

    private val reducer: SearchReducer,
    private val view: SearchView
) : Component() {

    override fun bindToView(containerView: ViewGroup?) {
        view.bind(containerView)
    }

    override fun wire() {
        // TODO use different scopes
        view.bindToScope(viewModelScope)
        interactor.bindToScope(viewModelScope)
        sideEffect.bindToScope(viewModelScope)
        reducer.bindToScope(viewModelScope)

        view.subscribe { uiAction ->
            interactor.handle(uiAction)
        }

        interactor.subscribe { interactorOutputAction ->
            // send output actions to reducer
            reducer.handle(interactorOutputAction)

            // send output action to side effects
            sideEffect.handle(interactorOutputAction)
        }

        // result of side-effects should be consumed by interactor
        sideEffect.subscribe { interactorOutputAction ->
            interactor.handle(interactorOutputAction)
        }

        reducer.subscribe { state ->
            view.render(state)
        }
    }

    override fun unWire() {
        view.bind(null)

        view.bindToScope(null)
        interactor.bindToScope(null)
        sideEffect.bindToScope(null)
        reducer.bindToScope(null)
    }
}