package com.glukharev.reactiveworkflow

import com.glukharev.framework.Action
import com.glukharev.framework.ScreenActionPool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BlahBlahScreenActionPool : ScreenActionPool {

    private var coroutineScope: CoroutineScope? = null

    private val outputActions =
        MutableStateFlow<Action?>(null)

    /**
     * Fragment or activity scope
     */
    override fun bindToScope(coroutineScope: CoroutineScope?) {
        this.coroutineScope = coroutineScope
    }

    @ExperimentalCoroutinesApi
    private val sharedAction = MutableStateFlow<Action?>(null)

    override fun pushSharedAction(action: Action) {
        sharedAction.value = action
    }

    override fun subscribeSharedAction(observer: (Action) -> Unit) {
        coroutineScope?.launch {
            outputActions.collect { action ->
                action?.let { observer.invoke(action) }
            }
        }
    }
}
