package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

interface Action

/**
 * Each screen should create separate instance of this class
 * to make sure that separate components can handle actions from another components (on same screen)
 */
class ScreenActionPool {

    private var coroutineScope: CoroutineScope? = null

    @ExperimentalCoroutinesApi
    private val sharedAction = MutableStateFlow<Action?>(null)

    /**
     * Fragment or activity scope
     */
    fun bindToScope(coroutineScope: CoroutineScope?) {
        this.coroutineScope = coroutineScope
    }

    fun pushSharedAction(action: Action) {
        sharedAction.value = action
    }

    fun subscribeSharedAction(observer: (Action) -> Unit) {
        coroutineScope?.launch {
            sharedAction.collect { action ->
                action?.let { observer.invoke(action) }
            }
        }
    }
}