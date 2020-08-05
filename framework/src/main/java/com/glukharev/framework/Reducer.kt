package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *
 */
abstract class Reducer<UIState> {
    private var coroutineScope: CoroutineScope? = null

    private val outputState =
        MutableStateFlow<UIState?>(null)

    fun bindToScope(coroutineScope: CoroutineScope?) {
        this.coroutineScope = coroutineScope
    }

    fun subscribe(observer: (UIState) -> Unit) {
        coroutineScope?.launch {
            outputState.collect { action ->
                action?.let { observer.invoke(action) }
            }
        }
    }

    fun handle(action: Action?) {
        outputState.value = handleAction(action)
    }

    protected abstract fun handleAction(action: Action?): UIState?
}