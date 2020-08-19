package com.glukharev.reactiveworkflow.progress

import androidx.lifecycle.viewModelScope
import com.glukharev.framework.ScreenActionPool
import com.glukharev.reactiveworkflow.Component

class ProgressComponent(
    private val reducer: ProgressReducer,
    private val view: ProgressView,
    private val screenActionPool: ScreenActionPool
) : Component() {

    override fun bindToView() {
        view.bind()
    }

    override fun wire() {
        // TODO use different scopes
        view.bindToScope(viewModelScope)
        reducer.bindToScope(viewModelScope)

        view.subscribe { uiAction ->
            reducer.handle(uiAction)
        }

        reducer.subscribe { state ->
            view.render(state)
        }

        screenActionPool.subscribeSharedAction { commonAction ->
            reducer.handle(commonAction)
        }
    }

    override fun unWire() {
//        view.bind(null)

        // todo for this just use viewModelScope
        view.bindToScope(null)
        reducer.bindToScope(null)
    }
}