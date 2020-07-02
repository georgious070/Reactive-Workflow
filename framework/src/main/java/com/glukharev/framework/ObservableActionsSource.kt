package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

interface ObservableActionsSource<Action> {
    fun send(action: Action)
    fun subscribe(observer: (Action) -> Unit)
}

class ObservableActionsSourceImpl<Action>(
    private val coroutineScope: CoroutineScope
) :
    ObservableActionsSource<Action> {

    private var channel = Channel<Action>(capacity = 1)

    override fun send(action: Action) {
        coroutineScope.launch {
            channel.send(action)
        }
    }

    override fun subscribe(observer: (Action) -> Unit) {
        coroutineScope.launch {
            channel.consumeEach(observer)
        }
    }
}