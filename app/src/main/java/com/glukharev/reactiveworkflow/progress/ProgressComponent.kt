package com.glukharev.reactiveworkflow.progress

import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.glukharev.reactiveworkflow.Component
import com.glukharev.reactiveworkflow.BlahBlahScreenActionPool

class ProgressComponent(
    private val reducer: ProgressReducer,
    private val view: ProgressView,
    private val screenActionPool: BlahBlahScreenActionPool
) : Component() {

    override fun bindToView(containerView: ViewGroup?) {
        view.bind(containerView)
    }

    override fun wire() {
        // TODO use different scopes
        view.bindToScope(viewModelScope)
        reducer.bindToScope(viewModelScope)

        view.subscribe { uiAction ->
        }

        reducer.subscribe { state ->
            view.render(state)
        }
    }

    override fun unWire() {
        view.bind(null)
        view.bindToScope(null)
        reducer.bindToScope(null)
    }
}