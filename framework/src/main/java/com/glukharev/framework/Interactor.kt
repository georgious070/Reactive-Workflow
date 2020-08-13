package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Business logic of component.
 * Used by component to make any side effects (for ex. receiving data from BE and store it to DB).
 * Can have reference just to Data layer API (in our case data layer will be represented only by repositories)
 */
abstract class Interactor {

    private var coroutineScope: CoroutineScope? = null

    private val outputActions =
        MutableStateFlow<Action?>(null)

    fun bindToScope(coroutineScope: CoroutineScope?) {
        this.coroutineScope = coroutineScope
    }

    fun subscribe(observer: (Action) -> Unit) {
        coroutineScope?.launch(Dispatchers.IO) {
            outputActions.collect { action ->
                action?.let { observer.invoke(action) }
            }
        }
    }

    fun handle(action: Action?) {
        outputActions.value = handleAction(action)
    }

    protected abstract fun handleAction(inputAction: Action?): Action?
}