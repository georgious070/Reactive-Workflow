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
    private val reducer: SearchReducer,
    private val view: SearchView
) : Component() {

    override fun bindToView(containerView: ViewGroup?) {
        view.bind(containerView)
    }

    override fun wire() {
        view.bindToScope(viewModelScope)
        interactor.bindToScope(viewModelScope)
        reducer.bindToScope(viewModelScope)

        view.subscribe { uiAction ->
            interactor.handle(uiAction)
        }

        interactor.subscribe { interactorOutputAction ->
            reducer.handle(interactorOutputAction)
        }

        reducer.subscribe { state ->
            view.render(state)
        }
    }

    override fun unWire() {
        view.bind(null)

        view.bindToScope(viewModelScope)
        interactor.bindToScope(viewModelScope)
        reducer.bindToScope(viewModelScope)
    }
}