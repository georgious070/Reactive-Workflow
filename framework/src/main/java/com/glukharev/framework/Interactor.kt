package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class Interactor<InputAction, OutputAction> {

    private var coroutineScope: CoroutineScope? = null

    private val outputActions =
        MutableStateFlow<OutputAction?>(null)

    fun bindToScope(coroutineScope: CoroutineScope) {
        this.coroutineScope = coroutineScope
    }

    fun subscribe(observer: (OutputAction) -> Unit) {
        coroutineScope?.launch {
            outputActions.collect { action ->
                action?.let { observer.invoke(action) }
            }
        }
    }

    fun handle(action: InputAction?) {
        outputActions.value = handleAction(action)
    }

    protected abstract fun handleAction(inputAction: InputAction?): OutputAction?
}